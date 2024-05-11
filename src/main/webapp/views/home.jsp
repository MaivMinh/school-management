<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <title>School Management</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100&family=Roboto:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style-starter.css">
</head>

<body>
<%@include file="header.jsp" %>
<!-- banner section -->
<section id="home" class="w3l-banner py-5">
    <div class="banner-content">
        <div class="container py-4">
            <div class="row align-items-center pt-sm-5 pt-4">
                <div class="col-md-6">
                    <h3 class="mb-lg-4 mb-3">Your Kids Deserve The<span class="d-block">Best Education</span>
                    </h3>
                    <p class="banner-sub">Active Learning, Expert Teachers & Safe Environment</p>
                    <div class="d-flex align-items-center buttons-banner">
                        <a href="/contact" class="btn btn-style mt-lg-5 mt-4">Admission Now</a>
                    </div>
                </div>
                <div class="col-md-6 right-banner-2 text-end position-relative mt-md-0 mt-5">
                    <div class="sub-banner-image mx-auto">
                        <img src="../assets/images/banner.png" class="img-fluid position-relative" alt=" ">
                    </div>
                    <div class="banner-style-1 position-absolute">
                        <div class="banner-style-2 position-relative">
                            <h4>Back to School</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- //banner section -->

<!-- home 4grids block -->
<section class="services-w3l-block py-5" id="features">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <p class="text-uppercase">Best Features</p>
            <h3 class="title-style">Achieve Your Goals With Eazy School</h3>
        </div>
        <div class="row">
            <div class="col-md-6 col-lg-3 d-flex align-items-stretch">
                <div class="icon-box icon-box-clr-1">
                    <div class="icon"><i class="fas fa-user-friends"></i></div>
                    <h4 class="title"><a href="/about">Expert Teachers</a></h4>
                    <p>Well experienced teachers for each subject. No common teacher for all subjects.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mt-md-0 mt-4">
                <div class="icon-box icon-box-clr-2">
                    <div class="icon"><i class="fas fa-book-reader"></i></div>
                    <h4 class="title"><a href="/about">Quality Education</a></h4>
                    <p>Best curriculum based on the Cambridge international framework.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mt-lg-0 mt-4">
                <div class="icon-box icon-box-clr-3">
                    <div class="icon"><i class="fas fa-user-graduate"></i></div>
                    <h4 class="title"><a>Alumni Support</a></h4>
                    <p>Life time Alumni support through various channels available .</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mt-lg-0 mt-4">
                <div class="icon-box icon-box-clr-4">
                    <div class="icon"><i class="fas fa-university"></i></div>
                    <h4 class="title"><a>Best Scholarships</a></h4>
                    <p>Best Scholarships available for the low income family students.</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- home 4grids block -->

<!-- home image with content block -->
<section class="w3l-servicesblock pt-lg-5 pt-4 pb-5 mb-lg-5" id="about">
    <div class="container pb-md-5 pb-4">
        <div class="row pb-xl-5 align-items-center">
            <div class="col-lg-6 position-relative home-block-3-left pb-lg-0 pb-5">
                <div class="position-relative">
                    <img src="../assets/images/img1.jpg" alt="" class="img-fluid radius-image">
                </div>
                <div class="imginfo__box">
                    <h6 class="imginfo__title">Get an Appointment Today!</h6>
                    <p>Call today to know more details<br> and to talk with our expert. </p>
                    <a href="tel:http://1(800)7654321"><i class="fas fa-phone-alt"></i> 1-800-856-4321</a>
                </div>
            </div>
            <div class="col-xl-5 col-lg-6 offset-xl-1 mt-lg-0 mt-5 pt-lg-0 pt-5">
                <h3 class="title-style">We Are The Best Choice For Your Child</h3>
                <p class="mt-4">With over 20 years of experience, best teachers, world-class infrastructure,
                    larger Alumni community, we are the best choice for your child education.</p>
                <ul class="mt-4 list-style-lis pt-lg-1">
                    <li><i class="fas fa-check-circle"></i>Special Education</li>
                    <li><i class="fas fa-check-circle"></i>Access more than 100K online courses</li>
                    <li><i class="fas fa-check-circle"></i>Traditional Academies</li>
                </ul>
                <a href="/contact" class="btn btn-style mt-5">Apply Now</a>
            </div>
        </div>
    </div>
</section>
<!-- //home image with content block -->

<!-- why choose block -->
<section class="w3l-service-1 py-5">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <p class="text-uppercase">Why Choose Us</p>
            <h3 class="title-style">Experienced Teachers And Safe environment</h3>
        </div>
        <div class="row content23-col-2 text-center">
            <div class="col-md-6">
                <div class="content23-grid content23-grid1">
                    <h4><a href="/about">Expert Teachers</a></h4>
                </div>
            </div>
            <div class="col-md-6 mt-md-0 mt-4">
                <div class="content23-grid content23-grid2">
                    <h4><a href="/about">Safe Environment</a></h4>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- //why choose block -->

<!-- stats block -->
<section class="w3-stats pt-4 pb-5" id="stats">
    <div class="container pb-md-5 pb-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <p class="text-uppercase">Our Statistics</p>
            <h3 class="title-style">We are Proud to Share with You</h3>
        </div>
        <div class="row text-center pt-4">
            <div class="col-md-3 col-6">
                <div class="counter">
                    <img src="../assets/images/icon-1.png" alt="" class="img-fluid">
                    <div class="timer count-title count-number mt-sm-1" data-to="2458" data-speed="1500"></div>
                    <p class="count-text">Current Students</p>
                </div>
            </div>
            <div class="col-md-3 col-6">
                <div class="counter">
                    <img src="../assets/images/icon-2.png" alt="" class="img-fluid">
                    <div class="timer count-title count-number mt-3" data-to="12" data-speed="1500"></div>
                    <p class="count-text">Our Branches</p>
                </div>
            </div>
            <div class="col-md-3 col-6 mt-md-0 mt-5">
                <div class="counter">
                    <img src="../assets/images/icon-3.png" alt="" class="img-fluid">
                    <div class="timer count-title count-number mt-3" data-to="128" data-speed="1500"></div>
                    <p class="count-text">Total Courses</p>
                </div>
            </div>
            <div class="col-md-3 col-6 mt-md-0 mt-5">
                <div class="counter">
                    <img src="../assets/images/icon-4.png" alt="" class="img-fluid">
                    <div class="timer count-title count-number mt-3" data-to="543" data-speed="1500"></div>
                    <p class="count-text">Awards Won</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- //stats block -->

<!-- testimonials block -->
<section class="w3l-index4 py-5" id="testimonials">
    <div class="container py-md-5 py-4">
        <div class="content-slider text-center">
            <div class="clients-slider">
                <div class="mask">
                    <ul>
                        <li class="anim1">
                            <img src="../assets/images/testi1.jpg" class="img-fluid rounded-circle"
                                 alt="client image"/>
                            <blockquote class="quote"><q>I am very happy that my son is studying in the
                                Eazy school. The support provided by the teachers is great and the curriculum
                                that they follow is of international standards.
                            </q></blockquote>
                            <div class="source">- Mario Spe</div>
                        </li>

                        <li class="anim2">
                            <img src="../assets/images/testi2.jpg" class="img-fluid rounded-circle"
                                 alt="client image"/>
                            <blockquote class="quote"><q>My child never felt pressure of studies in
                                the Eazy School. He is always encouraged to follow his own interest
                                and the school provides wide variety of programs for the students
                                outside studies.
                            </q></blockquote>
                            <div class="source">- Petey Cru</div>
                        </li>
                        <li class="anim3">
                            <img src="../assets/images/testi3.jpg" class="img-fluid rounded-circle "
                                 alt="client image"/>
                            <blockquote class="quote"><q>Loved the infrastructure and cleanly
                                environment that school maintains. This is very important
                                in the current pandemic situations.
                            </q></blockquote>
                            <div class="source">- Anna Sth</div>
                        </li>
                        <li class="anim4">
                            <img src="../assets/images/testi1.jpg" class="img-fluid rounded-circle"
                                 alt="client image"/>
                            <blockquote class="quote"><q>With the help of Cambridge
                                authorized programs, teachers always
                                enhancing my child classroom experiences
                                by applying new ideas & approaches.
                            </q></blockquote>
                            <div class="source">- Gail For</div>
                        </li>
                        <li class="anim5">
                            <img src="../assets/images/testi2.jpg" class="img-fluid rounded-circle"
                                 alt="client image"/>
                            <blockquote class="quote"><q>Eazy Schools supports both online and offline
                                classes based on the pandemic conditions to make sure that there is
                                no impact on the children studies. School staff always does their
                                best to accommodate parents request.
                            </q></blockquote>
                            <div class="source">- Boye Fra</div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- //testimonials block -->

<!-- courses section -->
<div class="w3l-grids-block-5 home-course-bg py-5" id="courses">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <p class="text-uppercase">Best Courses</p>
            <h3 class="title-style">Wide variety of Courses for your Child</h3>
        </div>
        <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6">
                <div class="coursecard-single">
                    <div class="grids5-info position-relative">
                        <img src="../assets/images/c1.jpg" alt="" class="img-fluid"/>
                        <div class="meta-list">
                            <a>Education</a>
                        </div>
                    </div>
                    <div class="content-main-top">
                        <div class="content-top mb-4 mt-3">
                            <ul class="list-unstyled d-flex align-items-center justify-content-between">
                                <li><i class="fas fa-book-open"></i> 43 Lesson</li>
                                <li><i class="fas fa-star"></i> 4.9</li>
                            </ul>
                        </div>
                        <h4><a>Educational Programs</a></h4>
                        <p>Educations programmes covering core concepts of Maths,
                            English, Science.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mt-md-0 mt-4">
                <div class="coursecard-single">
                    <div class="grids5-info position-relative">
                        <img src="../assets/images/c2.jpg" alt="" class="img-fluid"/>
                        <div class="meta-list">
                            <a class="sec-2">Meditation</a>
                        </div>
                    </div>
                    <div class="content-main-top">
                        <div class="content-top mb-4 mt-3">
                            <ul class="list-unstyled d-flex align-items-center justify-content-between">
                                <li><i class="fas fa-book-open"></i> 72 Lesson</li>
                                <li><i class="fas fa-star"></i> 4.6</li>
                            </ul>
                        </div>
                        <h4><a>Best Meditation Classes</a></h4>
                        <p>Special program focusing the meditation to encourage students
                            from early age.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mt-lg-0 mt-4">
                <div class="coursecard-single">
                    <div class="grids5-info position-relative">
                        <img src="../assets/images/c3.jpg" alt="" class="img-fluid"/>
                        <div class="meta-list">
                            <a class="sec-3">Games</a>
                        </div>
                    </div>
                    <div class="content-main-top">
                        <div class="content-top mb-4 mt-3">
                            <ul class="list-unstyled d-flex align-items-center justify-content-between">
                                <li><i class="fas fa-book-open"></i> 14 Lesson</li>
                                <li><i class="fas fa-star"></i> 5.0</li>
                            </ul>
                        </div>
                        <h4><a>Games Program in a Week</a></h4>
                        <p> Games program encouraging the children on the physical
                            activities.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center mt-sm-5 mt-4 pt-sm-0 pt-1">
            <a href="/courses" class="btn btn-style btn-style-secondary mt-sm-3">
                Browse more courses</a>
        </div>
    </div>
</div>
<!-- //courses section -->

<!-- call block -->
<section class="w3l-call-to-action-6">
    <div class="container py-md-5 py-sm-4 py-5">
        <div class="d-lg-flex align-items-center justify-content-between">
            <div class="left-content-call">
                <h3 class="title-big">Call To Enroll Your Child</h3>
                <p class="text-white mt-1">Begin the change today!</p>
            </div>
            <div class="right-content-call mt-lg-0 mt-4">
                <ul class="buttons">
                    <li class="phone-sec me-lg-4"><i class="fas fa-phone-volume"></i>
                        <a class="call-style-w3" href="tel:+1(23) 456 789 0000">+1(23) 673 458 1111</a>
                    </li>
                    <li><a href="/contact" class="btn btn-style btn-style-2 mt-lg-0 mt-3">Join for free</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>
<%@include file="footer.jsp" %>
<!-- //call block -->

<!-- Js scripts -->
<!-- move top -->
<button onclick="topFunction()" id="movetop" title="Go to top">
    <span class="fas fa-level-up-alt" aria-hidden="true"></span>
</button>
</body>
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
<!-- //common jquery plugin -->

<!-- /counter-->
<script src="../assets/js/counter.js"></script>
<!-- //counter-->

<!-- theme switch js (light and dark)-->
<script src="../assets/js/theme-change.js"></script>
<!-- //theme switch js (light and dark)-->

<!-- MENU-JS -->

<!-- //MENU-JS -->

<!-- disable body scroll which navbar is in active -->

<!-- //disable body scroll which navbar is in active -->

<!-- bootstrap -->
<!-- //bootstrap -->
<!-- //Js scripts -->
<script>
    $(function () {
        $('.navbar-toggler').click(function () {
            $('body').toggleClass('noscroll');
        })
    });
</script>
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
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="../assets/js/jquery-3.3.1.min.js"></script>
</html>
