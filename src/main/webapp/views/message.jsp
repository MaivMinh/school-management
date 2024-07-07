<%@include file="header.jsp" %>
<!-- //header -->

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Contact Messages</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Messages</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">

    <div class="table-responsive-md">
        <div class="row mb-4">
            <div class="col-md-7">
                <h3 class="heading-21921">Open Contact Messages</h3>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Mobile Num</th>
                <th scope="col">Email</th>
                <th scope="col">Subject</th>
                <th scope="col">Message</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contactMsg}" var="msg">
                <tr>
                    <td>${msg.name}</td>
                    <td>${msg.mobileNum}</td>
                    <td>${msg.email}</td>
                    <td>${msg.subject}</td>
                    <td>${msg.message}</td>
                    <c:if test="${msg.status.equalsIgnoreCase('open')}">
                        <td>
                            <a href="/close-msg?contact_id=${msg.contactId}" class="btn btn-style btn-style-3">CLOSE</a>
                        </td>
                    </c:if>
                    <c:if test="${msg.status.equalsIgnoreCase('closed')}">
                        <td>
                            <button style="color: red" type="button" class="btn" data-bs-toggle="button">CLOSED</button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-2 login-center text-start">
        <a href="/dashboard">
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