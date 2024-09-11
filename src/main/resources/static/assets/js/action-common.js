const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
const currentTheme = localStorage.getItem('theme');

if (currentTheme) {
    document.documentElement.setAttribute('data-theme', currentTheme);

    if (currentTheme === 'dark') {
        toggleSwitch.checked = true;
    }
}

function switchTheme(e) {
    if (e.target.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        localStorage.setItem('theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('theme', 'light');
    }
    processingBackground();
}

function processingBackground() {
    // Tìm thẻ div chứa danh sách các khoá học liên quan sau đó thay đổi back-ground.
    let container = document.querySelectorAll('.related-course');
    container.forEach((element, index) => {
        if (localStorage.getItem("theme") === 'dark') {
            // Nếu background là dark. Thì chuyển background của div sang sáng.
            element.classList.remove("related-course-light")
            element.classList.add("related-course-dark");
        } else {
            element.classList.remove("related-course-dark");
            element.classList.add("related-course-light");
        }
    })

    let commentSection = document.querySelector(".comment-section");
    if (commentSection != null) {
        if (localStorage.getItem("theme") === 'dark') {
            commentSection.classList.remove("related-course-light");
            commentSection.classList.add("related-course-dark");
        } else {
            commentSection.classList.remove("related-course-dark");
            commentSection.classList.add("related-course-light");
        }
    }
}

processingBackground();
toggleSwitch.addEventListener('change', switchTheme, false);

let user = sessionStorage.getItem('user');
if (user != null) {
    let headerImage = document.querySelector('#header-image');
    user = JSON.parse(user);
    headerImage.setAttribute('src', user.img);
}

// Thực hiện thêm một khoá học vào local storage.


// Thực hiện lấy danh sách khoá học từ giỏ hàng ở local storage rồi thêm vào sidebar.
function produceCourseComponent(courses) {
    if (courses != null) {
        let component = '';
        for (let course of courses) {
            // Thêm danh sách lecture vào phía sau course name.
            let list = '';
            for (let i = 0; i < course.lectures.length; i++) {
                if (i !== course.lectures.length - 1) {
                    list += course.lectures[i].name + ' - ';
                }   else    list += course.lectures[i].name;
            }
            if (list.length > 20)   {
                list = list.substring(0, 20);
                list += '...';
            }

            component += `
                <div style="display: flex; flex-direction: row; column-gap: 1rem; justify-content: start; align-items: center; border-bottom: 2px solid gray; height: 100px; padding: 10px 0;">
                    <img style="height: 100%; width: 30%; object-fit: cover" src="${course.img}" alt="course thumbnail">
                    <div style="display: flex; flex-direction: column; justify-content: start; align-items: start; height: 100%">
                        <a href="/courses/${course.courseId}">
                            <h5>${course.name}</h5>
                        </a>
                        <p style="font-size: 1rem; color: gray; font-weight: 500">${list}</p>
                        <p>
                            <span style="color: rgb(251, 176, 52)">$</span>
                            <span style="font-size: 1rem; font-weight: bold">${course.fee}</span>
                        </p>
                    </div>
                </div>  
            `
        }
        return component;
    }
    return ``;
}

let cartButton = document.querySelector('#cart-button');
cartButton.addEventListener('click', () => {
    /*
    * course : {
    *   courseId: ,
    *   name: ,
    *   fee: ,
    *   img: ,
    *   lecture: ,
    * }
    * */

    let component;
    let courses = localStorage.getItem('courses');
    if (courses != null) {
        courses = JSON.parse(courses);
        // Tạo ra component chứa toàn bộ thông tin các coures.
        component = produceCourseComponent(courses);
    } else  component = produceCourseComponent(null);
    let coursesComponent = document.querySelector('#courses-component');
    coursesComponent.innerHTML = component;
})

