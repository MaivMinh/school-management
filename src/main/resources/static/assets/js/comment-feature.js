import stompClient from "./socket-handling.js";
import fetchUser from "./home.js";

let user = JSON.parse(sessionStorage.getItem("user"));
if (user == null) {
    fetchUser()
        .then(() => console.log('success'))
        .catch(error => console.log(error))
}

let localURL = 'http://localhost:8080';
let azureURL = 'https://passio-school.azurewebsites.net';

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

function appendComment(comment) {
    // Hàm thực hiện việc append một tin nhắn vào comment box.
    if (comment.parentCommentId != null) {
        let replyComments = document.querySelector(`#reply-comment-${comment.parentCommentId}`);
        let milliseconds = Date.parse(comment.createdAt);
        let date = new Date(milliseconds);
        // Cộng 7 giờ để chuyển sang múi giờ Việt Nam
        date.setHours(date.getHours() + 7);
        let day = date.getDate();
        let month = date.getMonth() + 1;
        let year = date.getFullYear();
        let hours = date.getHours();   // Lấy giờ sau khi cộng thêm 7
        let minutes = date.getMinutes(); // Lấy phút
        // Đảm bảo giờ và phút có dạng 2 chữ số
        hours = hours.toString().padStart(2, '0');
        minutes = minutes.toString().padStart(2, '0');
        let created = `${day}/${month}/${year} ${hours}:${minutes}`;

        let first = `
            <div class="rounded-3 comment-box" id="comment-box-${comment.id}" style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: start; padding: 1.5rem; border: 1px solid rgb(224, 224, 224); background-color: rgb(255, 255, 255);">
                <img class="rounded-circle" src="${comment.img}" style="width: 25px; height: 25px; object-fit: cover; border: 2px solid rgb(221, 221, 221);" alt="user image">
                <div class="content-comment" style="display: flex; flex-direction: column; row-gap: 0.5rem; justify-content: start; align-items: start; flex-grow: 1;">
                    <a href="/users/${comment.sender}" style="display: flex; flex-direction: row;">
                    <h5 style="margin: 0; font-size: 1.1rem; color: #333;">${comment.name}</h5>
                        <p style="margin: 0 0 0 0.5rem; color: #666; font-size: 0.75rem; line-height: 1.75;">
                            ${created}
                        </p>
                    </a>
                    <p style="margin: 0; color: #666; font-size: 0.95rem; line-height: 1.5;">${comment.content}</p>
                </div>
            </div>
        `;
        first = first + replyComments.innerHTML;
        replyComments.innerHTML = first;
    } else {
        let commentSection = document.querySelector('#comment-section');
        let milliseconds = Date.parse(comment.createdAt);
        let date = new Date(milliseconds);
        // Cộng 7 giờ để chuyển sang múi giờ Việt Nam
        date.setHours(date.getHours() + 7);
        let day = date.getDate();
        let month = date.getMonth() + 1;
        let year = date.getFullYear();
        let hours = date.getHours();   // Lấy giờ sau khi cộng thêm 7
        let minutes = date.getMinutes(); // Lấy phút
        // Đảm bảo giờ và phút có dạng 2 chữ số
        hours = hours.toString().padStart(2, '0');
        minutes = minutes.toString().padStart(2, '0');
        let created = `${day}/${month}/${year} ${hours}:${minutes}`;


        let first = `
            <div style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: start; padding: 1.5rem; border: 1px solid #e0e0e0; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #fff;" class="rounded-3 comment-box individual-comment" id="comment-box-${comment.id}">
                <img style="width: 50px; height: 50px; object-fit: cover; border: 2px solid #ddd;" class="rounded-circle" src="${comment.img}" alt="user image">
                <div style="display: flex; flex-direction: column; row-gap: 0.5rem; justify-content: start; align-items: start; flex-grow: 1;" class="content-comment" id="content-comment-${comment.id}">
                    <a href="/users/${comment.sender}" style="display: flex; flex-direction: row">
                        <h5 style="margin: 0; font-size: 1.1rem; color: #333;">${comment.name}</h5>
                        <p style="margin: 0 0 0 0.5rem; color: #666; font-size: 0.75rem; line-height: 1.75;">
                            ${created}
                        </p>
                    </a>
                    <p style="margin: 0; color: #666; font-size: 0.95rem; line-height: 1.5;">${comment.content}</p>
                    <div class="reply-section" style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: center">
                        <i class="fa fa-thumbs-up" style="width: 1rem; color: #0d6efd"></i>
                        <button class="reply-button" style="border: none; cursor: pointer; font-size: 1rem; font-style: italic; background-color: transparent">
                            Reply
                        </button>
                        <input type="text" value="${comment.id}" hidden="">
                    </div>
                    <div class="show-more-comment-box">
                        <input type="text" value="196" id="show-more-comment-box-196" hidden="">
                        <button class="show-more-comment-button" style="border: none; background-color: transparent; font-size: 12px; color: #0c63e4">
                            Show more comments
                        </button>
                        <div class="spinner-border text-primary show-more-comment-spinner spinner-hidden" style="width: 1rem; height: 1rem; border-width: 2px" role="status">
                            <span class="visually-hidden">Loading...</span>
                        </div>
                    </div>
                    <div id="reply-comment-${comment.id}" class="reply-comments">

                    </div>
                </div>
            </div>
        `;
        first = first + commentSection.innerHTML;
        commentSection.innerHTML = first;
        addEventForShowMoreButton();
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

// Thực hiện chức năng fetch thêm comment khi user nhấn vào 'show more comments'.


function addEventForShowMoreButton() {
    const showMoreButtons = document.querySelectorAll(".show-more-comment-button");
    showMoreButtons.forEach(button => {
        button.addEventListener("click", showMoreHandling);
    })

    async function showMoreHandling(event) {
        // Hiển thị spinner sau đó fetch data tử server.
        const button = event.target;
        let spinner = button.nextElementSibling;
        spinner.classList.remove("spinner-hidden");
        spinner.classList.add("spinner-display");
        try {
            let input = button.previousElementSibling;  // Lấy được button HTML element.
            const id = input.value;
            button.remove();
            const data = await fetchData(azureURL + `/api/v1/comments/replies?id=${id}`, "GET", null);
            if (data.length === 0) {
                spinner.remove();
                return;
            }
            spinner.remove();
            // Thêm các reply comment vào phía dưới box.
            const replyCommentsPlaceHolder = document.querySelector(`#reply-comment-${id}`);
            let childDivs = replyCommentsPlaceHolder.querySelectorAll('.comment-box');
            let set = new Set();
            childDivs.forEach(child => {
                let id = parseInt(child.id.substring(child.id.lastIndexOf('-') + 1));
                console.log(id);
                set.add(id);
            })

            for (let comment of data) {
                if (set.has(comment.id)) continue;
                let milliseconds = Date.parse(comment.createdAt);
                let date = new Date(milliseconds);
                // Cộng 7 giờ để chuyển sang múi giờ Việt Nam
                date.setHours(date.getHours() + 7);
                let day = date.getDate();
                let month = date.getMonth() + 1;
                let year = date.getFullYear();
                let hours = date.getHours();   // Lấy giờ sau khi cộng thêm 7
                let minutes = date.getMinutes(); // Lấy phút
                // Đảm bảo giờ và phút có dạng 2 chữ số
                hours = hours.toString().padStart(2, '0');
                minutes = minutes.toString().padStart(2, '0');
                let created = `${day}/${month}/${year} ${hours}:${minutes}`;


                replyCommentsPlaceHolder.innerHTML += `
                    <div class="rounded-3 comment-box" id="comment-box-180" style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: start; padding: 1.5rem; border: 1px solid rgb(224, 224, 224); background-color: rgb(255, 255, 255);">
                        <img class="rounded-circle" src="${comment.img}" style="width: 25px; height: 25px; object-fit: cover; border: 2px solid rgb(221, 221, 221);" alt="user image">
                        <div class="content-comment" style="display: flex; flex-direction: column; row-gap: 0.5rem; justify-content: start; align-items: start; flex-grow: 1;">
                            <a href="/users/${comment.sender}" style="display: flex; flex-direction: row;">
                            <h5 style="margin: 0; font-size: 1.1rem; color: #333;">${comment.name}</h5>
                                <p style="margin: 0 0 0 0.5rem; color: #666; font-size: 0.75rem; line-height: 1.75;">
                                    ${created}
                                </p>
                            </a>
                            <p style="margin: 0; color: #666; font-size: 0.95rem; line-height: 1.5;">${comment.content}</p>
                        </div>
                    </div>
                `;
            }
        } catch (error) {
            console.error(error);
            spinner.remove();
        }
    }
}

addEventForShowMoreButton();