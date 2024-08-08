<%@include file="header.jsp" %>
<!-- //header -->

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Enrolled Courses</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Enrolled Courses</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->
<div class="site-section py-5">

    <div class="table-responsive-md">
        <div class="row mb-4">
            <div class="overview-wrap">
                <h3 class="heading-21921"> ${user.name} Enrolled Courses</h3>
            </div>
        </div>
        <table class="table table-striped dataTable table-hover">
            <thead class="table-dark">
            <tr>
                <th class="sorting" scope="col">
                    <a style="text-decoration: none" href="/student/courses?page=${page}&pageSize=${pageSize}&sortDir=${reverseDir}&sortField=courseId">Course ID</a>
                </th>
                <th class="sorting" scope="col">
                    <a style="text-decoration: none" href="/student/courses?page=${page}&pageSize=${pageSize}&sortDir=${reverseDir}&sortField=name">Course Name</a>
                </th>
                <th class="sorting" scope="col">
                    <a style="text-decoration: none" href="/student/courses?page=${page}&pageSize=${pageSize}&sortDir=${reverseDir}&sortField=fees">Fee</a>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${courses != null}">
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.name}</td>
                        <td>${course.fees}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="pagination-style text-center mt-5">
        <ul>
            <li>
                <c:if test="${page > 1}">
                    <a href="/student/courses?page=${page - 1}&pageSize=10&sortField=${sortField}&sortDir${sortDir}">
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
                           href="/student/courses?page=${i}&pageSize=10&sortField${sortField}&sortDir=${sortDir}">${i}</a>
                    </c:if>
                    <c:if test="${page != i}">
                        <a href="/student/courses?page=${i}&pageSize=10&sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                    </c:if>
                </li>
            </c:forEach>
        </span>
            <li>
                <c:if test="${page < totalPages}">
                    <a href="/student/courses?page=${page + 1}&pageSize=10&&sortField=${sortField}&sortDir=${sortDir}">
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
    <!--// pagination section -->
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