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