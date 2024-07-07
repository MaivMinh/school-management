<%@include file="header.jsp" %>

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Holidays</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Holidays</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- Holidays -->
<section class="w3l-timeline-1 py-5">
    <div class="container py-lg-5 py-4">
        <div class="title-heading-w3 text-center mb-sm-5 mb-4">
            <h5 class="title-small">Eazy School</h5>
            <h3 class="title-style">Awesome Holidays</h3>
        </div>
        <div class="row">
            <c:if test="${festival}">
                <div class="col-lg-6">
                    <h5 class="sub-title-timeline"><i class="fas fa-snowman"></i> Festival Holidays</h5>
                    <div class="timeline">
                        <div class="timeline">
                            <div class="column">
                                <c:forEach var="holiday" items="${FESTIVAL}">
                                    <div class="title">
                                        <h2>${holiday.reason}</h2>
                                    </div>
                                    <div class="description">
                                        <h6 class="fas fa-calendar-alt">${holiday.day}<i
                                                class="fas fa-calendar-alt"></i></h6>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${federal}">
                <div class="col-lg-6">
                    <h5 class="sub-title-timeline"><i class="fas fa-snowman"></i> Federal Holidays</h5>
                    <div class="timeline">
                        <div class="timeline">
                            <div class="column">
                                <c:forEach var="holiday" items="${FEDERAL}">
                                    <div class="title">
                                        <h2>${holiday.reason}</h2>
                                    </div>
                                    <div class="description">
                                        <h6 class="fas fa-calendar-alt">${holiday.day}<i
                                                class="fas fa-calendar-alt"></i></h6>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</section>

<!-- // Holidays -->

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

<!-- counter-->
<script src="../assets/js/counter.js"></script>
<!-- //counter-->

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
