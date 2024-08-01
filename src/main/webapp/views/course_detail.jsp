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
            <div class="coursecard-single">
                <div class="grids5-info position-relative">
                    <img src="${course.img}" alt="" class="img-fluid" id="img-hover-zoom">
                    <div class="meta-list">
                        <a>${course.category}</a>
                    </div>
                </div>
                <div class="content-main-top">
                    <div class="content-top mb-4 mt-3">
                        <ul class="list-unstyled d-flex align-items-center justify-content-start" style="column-gap: 1rem">
                            <li> <i class="fas fa-book-open"></i> ${course.lessons} Lesson</li>
                            <li> <i class="fas fa-star"></i> 5.0</li>
                        </ul>
                    </div>
                    <h4 class="mb-4"><a>${course.name}</a></h4>
                    <p>${course.description}</p>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-2 login-center text-start">
        <a href="/dashboard">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
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