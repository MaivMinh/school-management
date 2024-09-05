import stompClient from "./socket-handling.js";

const individualComments = document.querySelectorAll(".individual-comment");
let user = JSON.parse(sessionStorage.getItem("user"));
const contents = document.querySelectorAll(".content-comment");
const replyButtons = document.querySelectorAll(".reply-button");
let previousPostedComment;
replyButtons.forEach(button => {
    button.addEventListener("click", () => {
        // Tạo các block textArea cho user thực hiện bình luận.
        let form = document.createElement("form");
        form.classList.add("form-comment");
        form.method = "post";
        form.action = "/api/v1/comment";
        form.style.padding = "0.5rem 0.25rem";
        form.style.width = "100%";

        let input = document.createElement("input");
        // Thiết lập các thuộc tính hoặc nội dung cho input
        input.type = "text";
        input.style.width = "100%";
        input.style.height = "60px";
        input.style.border = "1px solid gray";
        input.style.borderRadius = "1rem";
        input.style.paddingLeft = "1rem";
        input.placeholder = "Enter your comment here";
        // Thực hiện thêm input vào form.
        form.append(input);
        form.addEventListener("submit", (event) => {
            event.preventDefault();
            let content = form.getElementsByTagName("input")[0].value;
            let courseId = document.getElementById("course-id").value;
            let parentId;
            for (let comments of individualComments) {
                if (comments.contains(form)) {
                    parentId = comments.id.charAt(comments.id.length-1);
                    previousPostedComment = comments;
                }
            }
            let comment = {
                'sender': user.userId,
                'name': user.name,
                'content': content,
                'createdAt': new Date(),
                'parentCommentId': parentId,
                'type': "REPLY",
                'courseId': courseId,
                'likes': 0,
                'disLikes': 0,
                'replies': 0
            }
            postComment(comment);
            form.remove();
        })

        for (let content of contents) {
            if (content.contains(button)) {
                let elements = content.getElementsByTagName("form");
                if (elements.length === 0)
                    content.appendChild(form);
                else {
                    content.removeChild(elements[0]);
                }
            }
        }

    })
})

function appendComment(comment) {
    // Hàm thực hiện việc append một tin nhắn vào comment box.
    if (previousPostedComment != null) {
        let content = previousPostedComment.getElementsByClassName("content-comment")[0];
        let div = document.createElement("div");
        div.classList.add("rounded-3");
        div.classList.add("comment-box");
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
        image.style.width = "25px";
        image.style.height = "25px";
        image.style.objectFit = "cover";
        image.style.border = "2px solid #DDD";
        image.classList.add("rounded-circle");
        image.src = user.img;
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
        <p style="margin: 0; color: #666; font-size: 0.95rem; line-height: 1.5;">${comment.content}</p>
        <div class="reply-section"
             style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: center;">
            <i class="fa fa-thumbs-up" style="width: 1rem; color: #0d6efd;"></i>
            <button class="reply-button"
                    style="border: none; cursor: pointer; font-size: 1rem; font-style: italic; background-color: transparent;">
                Reply
            </button>
        </div>`;

        div.append(childDiv);
        content.appendChild(div);
        previousPostedComment = null;
    } else {
        let object = document.querySelector(`#comment${comment.parentCommentId}`);
        let content = object.getElementsByClassName("content-comment")[0];
        let div = document.createElement("div");
        div.classList.add("rounded-3");
        div.classList.add("comment-box");
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
        image.style.width = "25px";
        image.style.height = "25px";
        image.style.objectFit = "cover";
        image.style.border = "2px solid #DDD";
        image.classList.add("rounded-circle");
        image.src = user.img;
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
        <p style="margin: 0; color: #666; font-size: 0.95rem; line-height: 1.5;">${comment.content}</p>
        <div class="reply-section"
             style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: center;">
            <i class="fa fa-thumbs-up" style="width: 1rem; color: #0d6efd;"></i>
            <button class="reply-button"
                    style="border: none; cursor: pointer; font-size: 1rem; font-style: italic; background-color: transparent;">
                Reply
            </button>
        </div>`;

        div.append(childDiv);
        content.appendChild(div);
    }
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