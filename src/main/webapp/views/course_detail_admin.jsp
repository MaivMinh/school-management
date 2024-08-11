<%@include file="header.jsp" %>
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Course Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/admin/courses">Courses</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Courses Detail</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">
    <div class="row justify-content-center">

        <div class="col-lg-4 col-md-6">
            <ul>
                <c:if test="${update != null && update == true}">
                    <li class="alert alert-success">Updated course successfully!</li>
                </c:if>
                <c:if test="${update != null && update == false}">
                    <li class="alert alert-danger">Failed to update course!</li>
                </c:if>
            </ul>

            <div class="coursecard-single">
                <div class="grids5-info position-relative">
                    <c:if test="${course.img != null}">
                        <img src="${course.img}" alt="" class="img-fluid" id="img-hover-zoom">
                    </c:if>
                    <c:if test="${course.img == null}">
                        <img src="../assets/images/bg1.jpg" class="img-fluid" id="img-hover-zoom" alt="">
                    </c:if>
                    <div class="meta-list">
                        <a>${course.category}</a>
                    </div>
                </div>
            </div>
            <form action="/admin/update-course" method="post" class="signin-form" enctype="multipart/form-data">
                <div class="content-main-top">
                    <div class="content-top mb-4 mt-3">
                        <ul class="list-unstyled d-flex align-items-center justify-content-start"
                            style="column-gap: 1rem">
                            <input type="number" name="courseId" value="${course.courseId}" hidden>
                            <div>
                                <label class="control-label" for="lessons">Lessons</label>
                                <input type="text" class="form-control" id="lessons" name="lessons"
                                       value="${course.lessons}" required>
                            </div>
                            <div>
                                <label class="control-label" for="vote">Star</label>
                                <input type="text" class="form-control" id="vote" name="vote"
                                       value="${course.vote != 0 ? course.vote: 5}" required>
                            </div>
                        </ul>
                    </div>
                    <div class="content-top mb-4 mt-3">
                        <c:if test="${lectures != null}">
                            <c:forEach items="${lectures}" var="lecture">
                                <ul class="list-unstyled d-flex align-items-center justify-content-start"
                                    style="column-gap: 1rem">
                                    <div>
                                        <label for="lecturer-id-${lecture.userId}">Lecturer ID</label>
                                        <input type="text" id="lecturer-id-${lecture.userId}" name="lecturer"
                                               class="form-control" value="${lecture.userId}" readonly required>
                                    </div>
                                    <div>
                                        <label class="control-label" for="lecturer-name-${lecture.name}">Lecturer
                                            Name</label>
                                        <input class="form-control" type="text" id="lecturer-name-${lecture.name}"
                                               name="lecturerName" value="${lecture.name}" readonly>
                                    </div>
                                </ul>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="input-grids">
                        <div class="mb-4">
                            <label class="control-label" for="name">Name </label>
                            <input type="text" name="name" value="${course.name}" id="name" class="form-control"
                                   required/>
                        </div>
                        <div class="mb-4">
                            <label for="introduction" class="control-label">Introduction </label>
                            <textarea type="text" name="introduction" id="introduction" cols="30" rows="3"
                                      class="form-control" required>${course.introduction}</textarea>
                        </div>
                        <div class="mb-4">
                            <label class="control-label" for="description">Description</label>
                            <textarea class="form-control w-100" name="description" id="description" cols="50"
                                      rows="6" required>${course.description}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Image</label>
                            <input class="form-control" type="file" id="formFile" name="file">
                        </div>
                    </div>
                </div>
                <div class="col-md-8 login-center text-start">
                    <a href="/admin/courses" class="new-user text-right">
                        <button class="btn btn-style btn-style-1 text-left">BACK</button>
                    </a>
                    <button class="btn btn-style btn-style-3 text-left">SUBMIT CHANGES</button>
                </div>
                <security:csrfInput/>
            </form>
        </div>
    </div>
</div>
</div>
<%@include file="footer.jsp" %>

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