<%@include file="header.jsp" %>
<!-- //header -->

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Register</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Register</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- contact block -->
<section class="w3l-contact py-5" id="contact">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <h3 class="title-style">Registration Form</h3>
        </div>
        <div class="row login-block">
            <div class="col-md-7 login-center">
                <c:if test="${error != null}">
                    <li class="alert alert-danger">${error}</li>
                </c:if>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li class="alert alert-danger"><c:out value="${error.getDefaultMessage()}"/></li>
                    </c:forEach>
                </ul>
                <form action="/public/create-user" method="post" class="signin-form">
                    <div class="input-grids">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="name" value="${user.getName()}" placeholder="Name"
                                       class="contact-input"/>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="mobileNum" value="${user.getMobileNum()}"
                                       placeholder="Mobile Number"
                                       class="contact-input"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="email" value="${user.getEmail()}" placeholder="Email"
                                       class="contact-input"/>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="confirmEmail" value="${user.getConfirmEmail()}"
                                       placeholder="Confirm Email"
                                       class="contact-input"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="password" name="password" value="${user.getPassword()}"
                                       placeholder="Password"
                                       class="contact-input"/>
                            </div>
                            <div class="col-sm-6">
                                <input type="password" name="confirmPassword" value="${user.getConfirmPassword()}"
                                       placeholder="Confirm Password"
                                       class="contact-input"/>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-3 login-center text-start">
                        <button class="btn btn-style btn-style-3">Register</button>
                    </div>
                    <security:csrfInput/>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- //contact block -->

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->