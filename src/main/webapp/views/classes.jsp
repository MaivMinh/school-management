<%@include file="header.jsp" %>
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Class Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Classes</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">
    <c:if test="${success != null}">
        <ul>
            <c:if test="${success == true}">
                <li class="alert alert-success">You have created a new class successfully!</li>
            </c:if>
            <c:if test="${success == false}">
                <li class="alert alert-danger">You have failure to create a new class!</li>
            </c:if>
        </ul>
    </c:if>
    <div class="table-responsive-class">
        <div class="row mb-4">
            <div class="overview-wrap">
                <h3 class="heading-21921">PASSIO School Classes Detail</h3>
                <button class="btn btn-style btn-style-3 text-left" type="button"
                        data-bs-toggle="modal" data-bs-target="#createClass">ADD CLASS
                </button>
            </div>
        </div>
        <div class="modal fade login-block" id="createClass" tabindex="-1" role="dialog"
             aria-labelledby="createClassModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header border-bottom-0">
                        <h5 class="modal-title" id="createClassLabel">Create New Class</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/admin/create-new-class" method="post" class="signin-form">
                        <div class="modal-body">
                            <div class="input-grids">
                                <label class="control-label" for="name">Class Name</label>
                                <input type="text" class="form-control" name="name" id="name" required
                                       placeholder="Enter class name">
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
                <th scope="col">Class ID</th>
                <th scope="col">Class Name</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${classes != null}">
                <c:forEach items="${classes}" var="element">
                    <tr>
                        <td>${element.classId}</td>
                        <td>${element.name}</td>
                        <td>
                            <a href="/admin/display-classes/${element.classId}" class="btn btn-success">VIEW</a>
                        </td>
                        <td>
                            <a href="/admin/delete-classes/${element.classId}" class="btn btn-warning">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
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