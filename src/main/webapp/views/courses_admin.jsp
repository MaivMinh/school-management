<%@include file="header.jsp" %>

<!-- //header -->
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Course Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Courses</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">
    <div class="table-responsive-md">
        <c:if test="${added != null}">
            <ul>
                <c:if test="${added == true}">
                    <li class="alert alert-success">The course has been added to the list successfully !</li>
                </c:if>
                <c:if test="${added == false}">
                    <li class="alert alert-warning">Failure to add new course into the list !</li>
                </c:if>
                <c:if test="${message != null}">
                    <li class="alert alert-warning">${message}</li>
                </c:if>
            </ul>
        </c:if>
        <div class="row mb-4">
            <div class="overview-wrap">
                <h3 class="heading-21921">PASSIO Course Details</h3>
                <button class="btn btn-style btn-style-3 text-left" type="button"
                        data-bs-toggle="modal" data-bs-target="#addCourse">ADD COURSE
                </button>
            </div>
        </div>
        <div class="modal fade login-block" id="addCourse" tabindex="-1" role="dialog"
             aria-labelledby="addCourseModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header border-bottom-0">
                        <h5 class="modal-title" id="addCourseLabel">Add New Course</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/admin/add-new-course" method="post" class="signin-form"
                          enctype="multipart/form-data">
                        <div class="modal-body">
                            <div class="input-grids">
                                <div>
                                    <label class="control-label" for="courseName">Course Name</label>
                                    <input type="text" class="form-control" name="name" id="courseName"
                                           required placeholder="Enter Course Name">
                                </div>
                                <div id="name-category">
                                    <div class="mb-3">
                                        <label for="formFile" class="form-label">Image</label>
                                        <input class="form-control" type="file" id="formFile" name="file">
                                    </div>
                                    <div>
                                        <label class="control-label" for="category">Category</label>
                                        <input type="text" id="category" name="category"
                                               placeholder="Enter Course Category" required>
                                    </div>
                                </div>
                                <div id="capacity-fee">
                                    <div>
                                        <label for="capacity" class="control-label">Capacity</label>
                                        <input type="text" name="capacity" id="capacity" value="50" required>
                                    </div>
                                    <div>
                                        <label class="control-label" for="fees">Fee</label>
                                        <input type="text" class="form-control" name="fee" id="fees"
                                               placeholder="Enter fee" required>
                                    </div>
                                </div>
                                <div id="course-date">
                                    <div>
                                        <label for="begin">Begin Date</label>
                                        <input type="date" id="begin" name="begin" required>
                                    </div>
                                    <div>
                                        <label for="end">End Date</label>
                                        <input type="date" id="end" name="end" required>
                                    </div>
                                </div>
                                <div id="lecture-id-name">
                                    <div>
                                        <label for="lecturerId">Lecturer ID</label>
                                        <input type="text" id="lecturerId" name="lecturer" class="form-control"
                                               value="${course.lecturer.userId}" required>
                                    </div>
                                    <div>
                                        <label class="control-label" for="lecturer">Lecturer Name</label>
                                        <input class="form-control" type="text" id="lecturer" name="lecturerName"
                                               value="${course.lecturer.name}" readonly>
                                    </div>
                                </div>
                                <div class="introduction-description">
                                    <div>
                                        <label class="control-label" for="introduction">Introduction</label>
                                        <textarea id="introduction" name="introduction" class="form-control"
                                                  type="text" rows="1" required></textarea>
                                    </div>
                                    <div>
                                        <label class="control-label" for="desc">Description</label>
                                        <textarea class="form-control" name="description" id="desc"
                                                  rows="5" required></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer border-top-0 d-flex justify-content-center">
                            <button type="submit" class="btn btn-style btn-style-3">Submit</button>
                        </div>
                    </form>
                </div>
                <security:csrfInput/>
                </form>
            </div>
        </div>
    </div>
    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th scope="col">Course ID</th>
            <th scope="col">Course Name</th>
            <th scope="col">Fee</th>
            <th scope="col">Begin Date</th>
            <th scope="col">End Date</th>
            <th scope="col">Enrolled</th>
            <th scope="col">State</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${courses != null}">
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.courseId}</td>
                    <td>
                        <a href="/courses/${course.courseId}">
                                ${course.name}
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="16" viewBox="0 0 24 24">
                                <path d="M16.949 7.051c.39.389.391 1.022.001 1.413l-8.485 8.486c-.392.391-1.023.391-1.414 0-.39-.39-.39-1.021.001-1.412l8.485-8.488c.39-.39 1.024-.387 1.412.001zm-5.805 10.043c-.164.754-.541 1.486-1.146 2.088l-1.66 1.66c-1.555 1.559-3.986 1.663-5.413.235-1.429-1.428-1.323-3.857.234-5.413l1.661-1.663c.603-.601 1.334-.98 2.087-1.144l1.934-1.934c-1.817-.306-3.829.295-5.313 1.783l-1.662 1.661c-2.342 2.34-2.5 5.978-.354 8.123 2.145 2.146 5.783 1.985 8.123-.354l1.66-1.66c1.486-1.487 2.089-3.496 1.783-5.314l-1.934 1.932zm3.222-15.231l-1.66 1.66c-1.486 1.488-2.089 3.499-1.783 5.317l1.935-1.935c.162-.753.54-1.485 1.146-2.087l1.66-1.66c1.556-1.559 3.984-1.663 5.413-.234 1.429 1.427 1.324 3.857-.233 5.415l-1.66 1.66c-.602.603-1.334.981-2.089 1.145l-1.934 1.934c1.818.306 3.827-.295 5.317-1.783l1.658-1.662c2.34-2.339 2.498-5.976.354-8.121-2.145-2.146-5.78-1.987-8.124.351z"/>
                            </svg>
                        </a>
                    </td>
                    <td>${course.fee}$</td>
                    <td>${course.begin}</td>
                    <td>${course.end}</td>
                    <td>${course.attendees}/${course.capacity}</td>
                    <c:if test="${course.state == 'OPEN'}">
                        <td><p class="btn btn-success">OPEN</p></td>
                    </c:if>
                    <c:if test="${course.state == 'CLOSED'}">
                        <td><p class="btn btn-danger">CLOSED</p></td>
                    </c:if>
                    <td>
                        <a href="/admin/courses/${course.courseId}/students"
                           class="btn btn-outline-info">DETAIL</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="pagination-style text-center mt-5">
        <ul>
            <li>
                <c:if test="${page > 1}">
                    <a href="/admin/courses?page=${page - 1}&pageSize=10&sortField=${sortField}&sortDir${sortDir}">
                        <span class="fa fa-angle-double-left" aria-hidden="true"></span>
                    </a>
                </c:if>
                <c:if test="${page <= 1}">
                    <a class="not-allowed" disabled="">
                        <span class="fa fa-angle-double-left" aria-hidden="true"></span>
                    </a>
                </c:if>
            </li>
            <span>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li>
                    <c:if test="${page == i}">
                        <a class="active"
                           href="/admin/courses?page=${i}&pageSize=10&sortField${sortField}&sortDir=${sortDir}">${i}</a>
                    </c:if>
                    <c:if test="${page != i}">
                        <a href="/admin/courses?page=${i}&pageSize=10&sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                    </c:if>
                </li>
            </c:forEach>
        </span>
            <li>
                <c:if test="${page < totalPages}">
                    <a href="/admin/courses?page=${page + 1}&pageSize=10&sortField=${sortField}&sortDir=${sortDir}">
                        <span class="fa fa-angle-double-right" aria-hidden="true"></span>
                    </a>
                </c:if>
                <c:if test="${page >= totalPages}">
                    <a class="not-allowed" disabled="">
                        <span class="fa fa-angle-double-right" aria-hidden="true"></span>
                    </a>
                </c:if>
            </li>
        </ul>
    </div>
    <div class="col-md-2 login-center text-center mt-4">
        <a href="/dashboard">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
    </div>
</div>

<!-- footer block -->
<%@include file="footer.jsp" %>
>
<!-- //footer block -->

<!-- Js scripts -->
<!-- move top -->
<button onclick="topFunction()" id="movetop" title="Go to top">
    <span class="fas fa-level-up-alt" aria-hidden="true"></span>
</button>
<script>
    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function () {
        scrollFunction()
    };

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            document.getElementById("movetop").style.display = "block";
        } else {
            document.getElementById("movetop").style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }

    function toDateInputValue(dateObject) {
        const local = new Date(dateObject);
        local.setMinutes(dateObject.getMinutes() - dateObject.getTimezoneOffset());
        return local.toJSON().slice(0, 10);
    };
    document.getElementById("begin").value = toDateInputValue(new Date());
    document.getElementById("end").value = toDateInputValue(new Date());
</script>
<!-- //move top -->

<!-- common jquery plugin -->
<script src="../assets/js/jquery-3.3.1.min.js"></script>
<!-- //common jquery plugin -->

<!-- theme switch js (light and dark)-->
<script src="../assets/js/theme-change.js"></script>
<!-- //theme switch js (light and dark)-->

<!-- MENU-JS -->
<script>
    $(window).on("scroll", function () {
        var scroll = $(window).scrollTop();

        if (scroll >= 80) {
            $("#site-header").addClass("nav-fixed");
        } else {
            $("#site-header").removeClass("nav-fixed");
        }
    });

    //Main navigation Active Class Add Remove
    $(".navbar-toggler").on("click", function () {
        $("header").toggleClass("active");
    });
    $(document).on("ready", function () {
        if ($(window).width() > 991) {
            $("header").removeClass("active");
        }
        $(window).on("resize", function () {
            if ($(window).width() > 991) {
                $("header").removeClass("active");
            }
        });
    });
</script>
<!-- //MENU-JS -->

<!-- disable body scroll which navbar is in active -->
<script>
    $(function () {
        $('.navbar-toggler').click(function () {
            $('body').toggleClass('noscroll');
        })
    });
</script>
<!-- //disable body scroll which navbar is in active -->

<!-- bootstrap -->
<script src="../assets/js/bootstrap.min.js"></script>
<!-- //bootstrap -->
<!-- //Js scripts -->
</body>
</html>