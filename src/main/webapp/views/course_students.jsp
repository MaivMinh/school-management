<%@include file="header.jsp" %>
<!-- //header -->
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Course Students Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="@{/home}">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Course Students</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">

    <div class="table-responsive-md">
        <ul>
            <c:if test="${added != null}">
                <c:if test="${added == true}">
                    <li class="alert alert-success">You have added student into the Course successfully !</li>
                </c:if>
                <c:if test="${added == false}">
                    <li class="alert alert-danger">Failure to add student into this Course !</li>
                </c:if>
            </c:if>
            <c:if test="${enrolled != null}">
                <c:if test="${enrolled == true}">
                    <li class="alert alert-info">Student have enrolled this Course !</li>
                </c:if>
            </c:if>
            <c:if test="${existed != null}">
                <c:if test="${existed == false}">
                    <li class="alert alert-danger">Student doesn't exist in our School !</li>
                </c:if>
            </c:if>
            <c:if test="${success != null}">
                <c:if test="${success == true}">
                    <li class="alert alert-success">You have deleted Student from this Course !</li>
                </c:if>
            </c:if>
            <c:if test="${error != null}">
                <c:if test="${error == true}">
                    <li class="alert alert-danger">Failure to delete Student from this Course !</li>
                </c:if>
            </c:if>
        </ul>
        <div class="row mb-4">
            <div class="overview-wrap">
                <c:if test="${courses != null}">
                    <h3 class="heading-21921">${courses.name} Course Students Details</h3>
                </c:if>
                <button class="btn btn-style btn-style-3 text-left" type="button"
                        data-bs-toggle="modal" data-bs-target="#addStudent">ADD STUDENT
                </button>
            </div>
        </div>
        <div class="modal fade login-block" id="addStudent" tabindex="-1" role="dialog"
             aria-labelledby="addStudentModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header border-bottom-0">
                        <h5 class="modal-title" id="addStudentModalLabel">Add Student to Course</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/admin/add-student-to-course" method="post" class="signin-form">
                        <input type="text" value="${courses.courseId}" name="courseId" hidden>
                        <div class="modal-body">
                            <div class="input-grids">
                                <label class="control-label" for="email">Student Email </label>
                                <input type="email" class="form-control" name="email" id="email"
                                       required placeholder="Enter Student email">
                            </div>
                        </div>
                        <div class="modal-footer border-top-0 d-flex justify-content-center">
                            <button type="submit" class="btn btn-style btn-style-3">Submit</button>
                        </div>
                        <security:csrfInput/>
                    </form>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Mobile Num</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${courses != null && courses.users != null}">
                <c:forEach items="${courses.users}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.mobileNum}</td>
                        <td>
                            <form action="/admin/delete-student-from-course" method="post">
                                <input type="text" value="${user.userId}" name="userId" hidden>
                                <input type="text" value="${courses.courseId}" name="courseId" hidden>
                                <button class="btn btn-warning">DELETE</button>
                                <security:csrfInput/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="col-md-2 login-center text-start">
        <a href="/admin/display-courses">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
    </div>
</div>

<!-- footer block -->
<%@include file="footer.jsp" %>
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