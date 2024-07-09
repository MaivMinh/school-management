<%@include file="header.jsp" %>

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Profile</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Profile</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- profile block -->
<section class="w3l-contact py-5" id="contact">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <h3 class="title-style">My Profile</h3>
        </div>
        <div class="row login-block">
            <div class="col-md-7 login-center">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li class="alert alert-danger"><c:out value="${error}"/></li>
                    </c:forEach>
                    <c:if test="${message != null}">
                        <li class="alert alert-success">${message}</li>
                    </c:if>
                </ul>
                <form action="/update-profile" method="post" class="signin-form">
                    <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
                        <h5 class="footer-title-29">Personal Details</h5>
                    </div>
                    <div class="input-grids">
                        <label class="control-label" for="name">Name:</label>
                        <input type="text" name="name" value="${profile.name}" id="name" class="contact-input"/>
                        <div class="row">
                            <div class="col-sm-6">
                                <label class="control-label" for="mobileNumber">Mobile Number:</label>
                                <input type="text" name="mobileNum" value="${profile.mobileNum}" id="mobileNumber"
                                       class="contact-input"/>
                            </div>
                            <div class="col-sm-6">
                                <label class="control-label" for="email">Email:</label>
                                <input type="text" value="${profile.email}" name="email" id="email"
                                       class="contact-input"/>
                            </div>
                        </div>
                    </div>
                    <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
                        <h5 class="footer-title-29">Address Details</h5>
                    </div>
                    <div class="input-grids">
                        <label class="control-label" for="address1">Address Line 1:</label>
                        <input type="text" value="${profile.address1}" name="address1" id="address1" class="contact-input"/>
                        <label for="address2">Address Line 2:</label>
                        <input type="text" value="${profile.address2}" name="address2" id="address2" class="contact-input"/>
                        <label class="control-label" for="city">City:</label>
                        <input type="text" value="${profile.city}" name="city" id="city" class="contact-input"/>
                        <div class="row">
                            <div class="col-sm-6">
                                <label class="control-label" for="state">State:</label>
                                <input type="text" value="${profile.state}" name="state" id="state"
                                       class="contact-input"/>
                            </div>
                            <div class="col-sm-6">
                                <label class="control-label" for="zipcode">Zip Code:</label>
                                <input type="text" value="${profile.zipcode}" name="zipcode" id="zipcode"
                                       class="contact-input"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 login-center text-start">
                        <button class="btn btn-style btn-style-3 text-left">Update</button>
                        <a href="/dashboard" class="new-user text-right">
                            <button class="btn btn-style btn-style-3 text-left">BACK</button>
                        </a>
                    </div>
                    <security:csrfInput/>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- //profile block -->

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->