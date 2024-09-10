const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
const currentTheme = localStorage.getItem('theme');

if (currentTheme) {
    document.documentElement.setAttribute('data-theme', currentTheme);

    if (currentTheme === 'dark') {
        toggleSwitch.checked = true;
    }
}

function switchTheme(e) {
    console.log('switching');
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