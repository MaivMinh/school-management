<!-- footer block -->
<footer class="w3l-footer-29-main">
    <div class="footer-29 pt-5 pb-4">
        <div class="container pt-md-4">
            <div class="row footer-top-29">
                <div class="col-lg-4 col-md-6 footer-list-29">
                    <h6 class="footer-title-29">Contact Info </h6>
                    <p class="mb-2 pe-xl-5">Address : Eazy School, 10001, 5th Avenue, #06 lane street, NY - 62617.
                    </p>
                    <p class="mb-2">Phone Number : <a href="tel:+1(21) 234 4567">+1(21) 673 4587</a></p>
                    <p class="mb-2">Email : <a href="mailto:info@eazyschool.com">info@eazyschool.com</a></p>
                </div>
                <div class="col-lg-2 col-md-3 col-6 footer-list-29 mt-md-0 mt-4">
                    <ul>
                        <h6 class="footer-title-29">Quick Links</h6>
                        <li><a href="/about">About Us</a></li>
                        <li><a href="/courses">Courses</a></li>
                        <li><a href="/contact">Become a Teacher</a></li>
                        <li><a href="/contact">Contact Us</a></li>
                        <li><a href="/contact">Career</a></li>
                    </ul>
                </div>
                <div class="col-lg-2 col-md-3 col-6 ps-lg-5 ps-lg-4 footer-list-29 mt-md-0 mt-4">
                    <ul>
                        <h6 class="footer-title-29">Explore</h6>
                        <li><a href="/holidays">Holidays</a></li>
                        <li><a href="/contact">Privacy policy</a></li>
                        <li><a href="/contact">Contact Us</a></li>
                        <li><a href="/contact">License & uses</a></li>
                        <li><a href="/courses">Courses</a></li>
                    </ul>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-8 footer-list-29 mt-lg-0 mt-4 ps-lg-5">
                    <h6 class="footer-title-29">Subscribe</h6>
                    <form action="#" class="subscribe d-flex" method="post">
                        <input type="email" name="email" placeholder="Email Address" required="">
                        <button class="button-style"><span class="fa fa-paper-plane"
                                                           aria-hidden="true"></span></button>
                    </form>
                    <p class="mt-3">Subscribe to our mailing list and get updates to your email inbox.</p>
                </div>
            </div>
            <!-- copyright -->
            <p class="copy-footer-29 text-center pt-lg-2 mt-5 pb-2">© 2023 Eazy School. All rights reserved. Design
                by <a href="https://w3layouts.com/" target="_blank">W3Layouts</a></p>
            <!-- //copyright -->
        </div>
    </div>
</footer>
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
<script src="/assets/js/jquery-3.3.1.min.js"></script>
<!-- //common jquery plugin -->

<!-- theme switch js (light and dark)-->
<script src="/assets/js/theme-change.js"></script>
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

    //Main navigation Active PassioClass Add Remove
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
<script src="/assets/js/bootstrap.min.js"></script>
<!-- //bootstrap -->
<!-- //Js scripts -->
</body>
</html>