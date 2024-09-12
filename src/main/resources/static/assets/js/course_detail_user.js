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
            if (_course.courseId === course.courseId) return;
        }
        courses.push(course);
        // Push lại vào local.
        localStorage.removeItem('courses');
        localStorage.setItem('courses', JSON.stringify(courses));
    } else {
        let courses = [];
        courses.push(course);
        courses = JSON.stringify(courses);
        localStorage.setItem('courses', courses);
    }
}
let addToCartButton = document.querySelector('#add-to-cart-button');
addToCartButton.addEventListener('click', addCouresToCart);