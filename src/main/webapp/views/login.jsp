<%@include file="header.jsp" %>

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">LogIn</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>LogIn</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- login block -->
<section class="w3l-contact py-5" id="contact">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <h3 class="title-style">LogIn</h3>
        </div>
        <div class="row login-block">
            <div class="col-md-6 login-center">
                <form action="/public/login" method="post" class="signin-form">
                    <div class="col-md-8 login-center input-grids">
                        <c:if test="${error != null}">
                            <li class="alert alert-danger">${error}</li>
                        </c:if>
                        <c:if test="${message != null}">
                            <li class="alert alert-success">${message}</li>
                        </c:if>
                        <c:if test="${register != null}">
                            <li class="alert alert-success">${register}</li>
                        </c:if>
                        <input type="text" name="username" id="username" placeholder="Username" autofocus
                               class="login-input"/>
                        <input type="password" name="password" id="password" placeholder="Password"
                               class="login-input"/>
                    </div>
                    <div class="col-md-8 login-center text-start">
                        <button class="btn btn-style btn-style-3 text-left">Log In</button>
                        <a class="new-user text-right" href="/public/register">New User ?</a>
                    </div>
                    <security:csrfInput/>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- //login block -->

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
<script src="../assets/js/bootstrap.min.js"></script>
<!-- //bootstrap -->
<!-- //Js scripts -->
</body>

</html>