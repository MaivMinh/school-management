import stompClient from "./socket-handling.js";

let user = JSON.parse(sessionStorage.getItem("user"));
function sendMessage(form, parentId) {
    let content = form.getElementsByTagName("input")[0].value;
    let courseId = document.getElementById("course-id").value;
    let comment = {
        'sender': user.userId,
        'name': user.name,
        'content': content,
        'createdAt': new Date(),
        'img': user.img,
        'parentCommentId': parentId,
        'type': parentId != null ? "COMMENT" : "REPLY",
        'courseId': courseId,
        'likes': 0,
        'disLikes': 0,
        'replies': 0
    }
    postComment(comment);
}

function replyButtonLoader() {
    const replyButtons = document.querySelectorAll(".reply-button");
    replyButtons.forEach(button => {
        button.addEventListener("click", function handleClick() {
            let replySection = button.parentElement;
            if (replySection.nextElementSibling != null && replySection.nextElementSibling.tagName.toLowerCase() === 'form') {
                return;
            }
            let form = document.createElement("form");
            form.classList.add("form-comment");
            form.method = "post";
            form.action = "/api/v1/comment";
            form.style.padding = "0.5rem 0.25rem";
            form.style.width = "100%";

            let input = document.createElement("input");
            input.type = "text";
            input.style.width = "100%";
            input.style.height = "60px";
            input.style.border = "1px solid gray";
            input.style.borderRadius = "1rem";
            input.style.paddingLeft = "1rem";
            input.placeholder = "Enter your comment here";
            // Thực hiện thêm input vào form.
            form.append(input);
            replySection.insertAdjacentElement('afterend', form);
            form.addEventListener("submit", (event) => {
                event.preventDefault();
                let parentId = button.nextElementSibling.value;
                sendMessage(form, parentId);
                form.remove();
            })
        })
    })
}
replyButtonLoader();


// Đoạn mã này giành cho form comment để user để lại comment của chính mình.
let formComments = document.querySelectorAll(".form-comment");
formComments.forEach(form => {
    form.addEventListener("submit", (event) => {
        event.preventDefault();
        sendMessage(form, null);
        form.getElementsByTagName("input")[0].value = '';
    })
})

function produceDivTagFromComment(comment, reply) {
    let div = document.createElement("div");
    div.classList.add("rounded-3");
    div.classList.add("comment-box");
    div.id = `comment-box-${comment.id}`
    div.style.display = "flex";
    div.style.flexDirection = "row";
    div.style.columnGap = "1rem";
    div.style.justifyContent = "start";
    div.style.alignItems = "start";
    div.style.padding = "1.5rem";
    div.style.border = "1px solid #e0e0e0";
    div.style.boxShadow = "0 4px 8px rgba(0, 0, 0, 0.1);"
    div.style.backgroundColor = "#FFF";
    let image = document.createElement("img");
    image.style.width = reply ? "25px" : "50px";
    image.style.height = reply ? "25px" : "50px";
    image.style.objectFit = "cover";

    image.style.border = "2px solid #DDD";
    image.classList.add("rounded-circle");
    image.src = comment.img;
    div.append(image);
    let childDiv = document.createElement("div");
    childDiv.style.display = "flex";
    childDiv.style.flexDirection = "column";
    childDiv.style.rowGap = "0.5rem";
    childDiv.style.justifyContent = "start";
    childDiv.style.alignItems = "start";
    childDiv.style.flexGrow = "1";
    childDiv.classList.add("content-comment");
    childDiv.innerHTML = `<a href="/users/${comment.sender}" style="display: flex; flex-direction: row;">
            <h5 style="margin: 0; font-size: 1.1rem; color: #333;">${comment.name}</h5>
            <p style="margin: 0 0 0 0.5rem; color: #666; font-size: 0.75rem; line-height: 1.75;">
                2 hours ago
            </p>
        </a>
        <p style="margin: 0; color: #666; font-size: 0.95rem; line-height: 1.5;">${comment.content}</p>`;
    if (!reply) {
        childDiv.innerHTML += `
            <div class="reply-section"
             style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: center">
                <i class="fa fa-thumbs-up" style="width: 1rem; color: #0d6efd"></i>
                <button class="reply-button"
                        style="border: none; cursor: pointer; font-size: 1rem; font-style: italic; background-color: transparent">
                    Reply
                </button>
                <input type="text" value="${comment.id}" hidden>
            </div>
        `
    }
    div.append(childDiv);
    return div;
}

function appendComment(comment) {
    // Hàm thực hiện việc append một tin nhắn vào comment box.
    if (comment.parentCommentId != null) {
        let div = produceDivTagFromComment(comment, true);
        let commentBox = document.querySelector(`#comment-box-${comment.parentCommentId}`);
        let content = commentBox.getElementsByClassName("content-comment")[0];
        content.appendChild(div);
    } else {
        let div = produceDivTagFromComment(comment, false);
        let commentSection = document.querySelectorAll(".comment-section")[0];
        commentSection.appendChild(div);
    }
    replyButtonLoader();
}

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/comments', (comment) => {
        appendComment(JSON.parse(comment.body));
    });
};

function postComment(comment) {
    stompClient.publish({
        destination: "/app/comment",
        // Lưu thông tin của comment vào body dưới dạng JSON object.
        body: JSON.stringify(comment)
    });
}

async function fetchData(url, method, body) {
    // Function to fetch user information
    try {
        let response;
        if (method === 'GET') {
            response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            });
        } else if (method === 'POST') {
            response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: body
            });
        }
        // Check if the response is ok (status code 200-299)
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        // Parse the JSON response
        const data = await response.json();
        return data;

    } catch (error) {
        console.error('Error fetching user information:', error);
    }
}

// Thực hiện chức năng fetch thêm comment khi user nhấn vào button.
const showMoreButtons = document.querySelectorAll(".show-more-comment-button");
showMoreButtons.forEach(button => {
    button.addEventListener("click", async () => {
        // Hiển thị spinner sau đó fetch data tử server.
        let spinner = button.nextElementSibling;
        spinner.classList.remove("spinner-hidden");
        spinner.classList.add("spinner-display");
        try {
            let input = button.previousElementSibling;  // Lấy được button HTML element.
            const id = input.value;
            button.remove();
            const data = await fetchData(`http://localhost:8080/api/v1/comments/replies?id=${id}`, "GET", null);
            if (data.length === 0) {
                spinner.remove();
                return;
            }
            spinner.remove();
            // Thêm các reply comment vào phía dưới box.
            const replyCommentsPlaceHolder = document.querySelector(`#reply-comment-${id}`);
            for (let comment of data) {
                let div = produceDivTagFromComment(comment, true);
                replyCommentsPlaceHolder.append(div);
            }
        } catch (error) {
            console.error(error);
            spinner.remove();
        }
    })
})


// Thực hiện thêm course vào giỏ hàng -> local storage. Lưu ý, chúng ta chỉ cần lưu vào local thôi. Còn user họ đăng nhập chưa thì không quan trọng.
function addCouresToCart() {
    /*
    * course : {
    *   courseId: ,
    *   name: ,
    *   fee: ,
    *   img: ,
    *   lecture: [{userId: , name: }, {userId: , name: }]
    * }
    * */

    let id = document.querySelector('#course-id').value;
    let name = document.querySelector('#course-name').innerText;
    let fee = parseInt(document.querySelector('#course-fee').innerText);
    let img = document.querySelector('.course-img').src;
    let lectures = [];
    let i = 0;
    let list = document.querySelectorAll('.list-lectures');
    list.forEach(element => {
        let a = element.getElementsByTagName('a')[0];
        let lectureId = parseInt(a.href.substring(a.href.lastIndexOf('/') + 1));
        let lectureName = a.text;
        lectures[i++] = {userId: lectureId, name: lectureName};
    })
    let course = {
        courseId: parseInt(id),
        name: name,
        fee: fee,
        img: img,
        lectures: lectures
    }

    // Thực hiện lấy đối tượng courses trong local ra kiểm tra. Sau đó thêm course vừa tạo vào.
    let courses = localStorage.getItem('courses');
    if (courses != null) {
        courses = JSON.parse(courses);
        for (let _course of courses) {
            if (_course.courseId === course.courseId)   return;
        }
        courses.push(course);
        // Push lại vào local.
        localStorage.removeItem('courses');
        localStorage.setItem('courses', JSON.stringify(courses));
    }   else    {
        let courses = [];
        courses.push(course);
        courses = JSON.stringify(courses);
        localStorage.setItem('courses', courses);
    }
}
let addToCartButton = document.querySelector('#add-to-cart-button');
addToCartButton.addEventListener('click', addCouresToCart);