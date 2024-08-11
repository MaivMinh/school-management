<%@include file="header.jsp" %>
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Course Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/courses">Courses</a></li>
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
            <div style="display: flex; flex-direction: row; column-gap: 2rem;">
                <div class="coursecard-single" style="width: 50%; display: block">
                    <div class="grids5-info position-relative" style="width: 100%">
                        <c:if test="${course.img != null}">
                            <img src="${course.img}" alt="" class="img-fluid" id="img-hover-zoom" style="width: 100%">
                        </c:if>
                        <c:if test="${course.img == null}">
                            <img src="../assets/images/bg1.jpg" class="img-fluid" id="img-hover-zoom"
                                 style="width: 100%"
                                 alt="">
                        </c:if>
                        <div class="meta-list">
                            <a>${course.category}</a>
                        </div>
                    </div>
                    <div style="width: 100%; margin-top: 1rem;">
                        <p style="font-size: 32px; font-weight: 700; margin-bottom: 1rem">${course.name}</p>
                        <p style="font-size: 19px; font-weight: 400; margin-bottom: 1.5rem; ">${course.introduction}</p>
                    </div>
                </div>
                <div class="content-main-top">
                    <div class="content-top mb-4 mt-3">
                        <ul class="list-unstyled d-flex align-items-center justify-content-start gap-4">
                            <li style="font-size: 1.5rem"><i class="fas fa-book-open"
                                                             style="font-size: 1.5rem"></i> ${course.lessons} lessons
                            </li>
                            <li style="font-size: 1.5rem"><i class="fas fa-star"
                                                             style="font-size: 1.5rem"></i> ${course.vote != null ? course.vote: 5.0}
                            </li>
                        </ul>
                    </div>
                    <div class="content-top mb-4 mt-3">
                        <ul class="list-unstyled d-flex align-items-center justify-content-start gap-4">
                            <li style="font-size: 1.5rem"><i class="fas fa-user-alt" style="font-size: 1.5rem"></i>
                                Created by <a style="font-size: 1.5rem;"
                                              href="#">Lecture name will present here</a></li>
                            <li style="font-size: 1rem"><i class="fas fa-address-book" style="font-size: 1rem"></i> <a
                                    href="mailto: maiminh1307200@gmail.com">Lecture email will present here</a></li>
                        </ul>
                    </div>
                    <div class="content-top mb-4 mt-3">
                        <ul class="list-unstyled d-flex align-items-center justify-content-start gap-4">
                            <li style="font-size: 1.5rem;"><i class="fas fa-money-check-alt"
                                                             style="font-size: 1.5rem"></i> ${course.fees}</li>
                        </ul>
                    </div>
                    <div class="mb-4 mt-3 content-top">
                        <div style="width: 100%; margin-bottom: 1rem;">
                            <a href="#">
                                <button class="btn btn-info btn-lg" style="margin-right: 1rem; color: white">Add to card</button>
                            </a>
                            <a href="#">
                                <button class="btn btn-danger btn-lg"><i class="fa fa-heart"></i></button>
                            </a>
                        </div>
                        <div style="width: 100%">
                            <a href="#">
                                <button class="btn btn-info btn-lg" style="width: 100%; color: white">REGISTER NOW
                                </button>
                            </a>
                        </div>
                    </div>
                    <div class="mb-4 mt-3 content-top">
                        <p style="font-size: 1.2rem; font-weight: 600; margin-bottom: 0.5rem">This course will
                            include: </p>
                        ${course.description}
                    </div>
                </div>
            </div>
            <div id="course-detail-comment" style="width: 80%">
                <div style="width: 100%; margin-left: auto; margin-right: auto;">
                    <label class="control-label" for="comment">Comment</label>
                    <input type="text" class="form-control" id="comment" name="comment">
                </div>
            </div>
            <div class="col-md-8 login-center text-start">
                <a href="/admin/courses" class="new-user text-right">
                    <button class="btn btn-style btn-style-1 text-left">BACK</button>
                </a>
            </div>
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