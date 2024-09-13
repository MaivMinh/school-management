function produceCourseComponent(courses) {
    let component = '';
    for (let course of courses) {
        let lectures = course.lectures;
        let authors = '';
        for (let i = 0; i < lectures.length; i++) {
            if (i < lectures.length - 1) {
                authors += lectures[i].name + ' - ';
            } else authors += lectures[i].name;
        }
        component += `
            <div class="course-detail" style="border-top: 1px solid #d1d7dc; padding: 1rem 0">
                <div class="area-1"
                     style="display: flex; flex-direction: column; justify-content: start; align-items: center">
                    <img class="rounded-2" style="width: 90%; object-fit: cover" src="${course.img}"
                         alt="course image">
                </div>
                <div class="area-2" style="display: flex; flex-direction: column; row-gap: 0.8rem; justify-content: start; align-items: start;">
                    <h5>${course.name}</h5>
                    <p style="color: gray; font-weight: 500">${authors}</p>
                    <p>
                        <span style="color: #fbb034; font-weight: bold">
                            <i class="fa fa-star"></i>
                        </span>
                        <span style="font-weight: bold">4.5</span>
                    </p>
                </div>
                <div class="area-3" style="display: flex; flex-direction: column; row-gap: 0; justify-content: start; align-items: end; ">
                    <p>
                        <span style="color: #157347; font-weight: bold; font-size: 1.5rem; ">$</span>
                        <span style="color: #157347; font-weight: lighter; font-size: 1.5rem;">${course.fee}</span>
                    </p>
                    <p style="color: darkgray; font-weight: normal; text-decoration: line-through ">
                        $ 1500
                    </p>
                    <button class="delete-button-shopping-cart" style="background-color: transparent; border: none; margin-top: 1rem;">
                        <i style="color: red; " class="fa fa-trash"></i>
                    </button>
                    <input type="text" value="${course.courseId}" hidden>
                </div>
            </div>
        `;
    }
    return component;
}
function loadCoursesList() {
    let courses = localStorage.getItem('courses');
    if (courses == null) return;
    courses = JSON.parse(courses);

    // lấy div có mục đích chứa danh sách course.
    let container = document.querySelector('#courses-container')
    container.innerHTML = produceCourseComponent(courses);
    addEventForDeleteButton();
    updateCourseNums(courses.length);
    calculateTotalCost(courses);
}
loadCoursesList();

function handleDeleteCourse(event) {
    const button = event.target;
    let courseId = parseInt(button.parentElement.nextElementSibling.value);
    let courses = localStorage.getItem('courses');
    courses = JSON.parse(courses);
    for (let i = 0; i < courses.length; i++) {
        if (parseInt(courses[i].courseId) === courseId) {
            courses.splice(i, 1);
            break;
        }
    }

    // reload lại giỏ hàng.
    let component = produceCourseComponent(courses);
    let coursesComponent = document.querySelector('#courses-container');
    coursesComponent.innerHTML = component;
    addEventForDeleteButton();
    updateCourseNums(courses.length);
    calculateTotalCost(courses);

    localStorage.removeItem('courses');
    courses = JSON.stringify(courses);
    localStorage.setItem('courses', courses);
}

function addEventForDeleteButton() {
    let deleteCourseButton = document.querySelectorAll('.delete-button-shopping-cart');
    deleteCourseButton.forEach(button => {
        button.addEventListener('click', handleDeleteCourse);
    })
}

function updateCourseNums(num) {
    document.querySelector('#course-nums').innerHTML = `${num} courses into cart`;
}

function calculateTotalCost(courses) {
    // Hàm tính tổng chi phí và update.
    let sum = 0;
    for (let course of courses) {
        sum += course.fee;
    }
    document.querySelector('#total').innerHTML = `
        <span style="font-weight: bold; font-size: 2rem;">$</span>
        <span style="font-weight: bold; font-size: 2rem;">${sum}</span>
    `;
}