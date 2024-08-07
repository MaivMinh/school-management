<%@include file="header.jsp" %>
<!-- //header -->

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Contact Us</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Contact Us</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- contact block -->
<section class="w3l-contact py-5" id="contact">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <p class="text-uppercase">Get In Touch</p>
            <h3 class="title-style">Contact Us</h3>
        </div>
        <div class="row contact-block">
            <div class="col-md-7 contact-right">
                <ul>
                    <c:forEach items="${errors}" var="error" >
                        <li class="alert alert-danger" ><c:out value="${error.getDefaultMessage()}"/></li>
                    </c:forEach>
                    <c:if test="${message != null}">
                        <li class="alert alert-success">${message}</li>
                    </c:if>
                </ul>
                <form action="/public/save-msg" method="post" class="signin-form" >
                    <div class="input-grids">
                        <div class="row">
                            <div class="col-sm-6">
                                <input type="text" name="name" id="name" placeholder="Your Name"
                                       class="contact-input" value="${contact.getName()}" required/>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="mobileNum" id="mobileNum" placeholder="Your Mobile Number"
                                       class="contact-input" value="${contact.getMobileNum()}"/>
                            </div>
                        </div>
                        <input type="email" name="email" id="email" placeholder="Your Email"
                               class="contact-input" value="${contact.getEmail()}" required/>
                        <input type="text" name="subject" id="subject" placeholder="Subject"
                               class="contact-input" value="${contact.getSubject()}"/>
                        <input type="text" name="created_by" value="${username}" hidden="hidden" />
                    </div>
                    <div class="form-input">
                        <textarea name="message" id="message" placeholder="Type your message here">${contact.getMessage()}</textarea>
                    </div>
                    <div class="text-start">
                        <button class="btn btn-style btn-style-3">Send Message</button>
                    </div>
                </form>
            </div>
            <div class="col-md-5 ps-lg-5 mt-md-0 mt-5">
                <div class="contact-left">
                    <div class="cont-details">
                        <div class="d-flex contact-grid">
                            <div class="cont-left text-center me-3">
                                <i class="fas fa-building"></i>
                            </div>
                            <div class="cont-right">
                                <h6>School Address</h6>
                                <p>Eazy School, 10001, 5th Avenue, #06 lane street, NY - 10017.</p>
                            </div>
                        </div>
                        <div class="d-flex contact-grid mt-4 pt-lg-2">
                            <div class="cont-left text-center me-3">
                                <i class="fas fa-phone-alt"></i>
                            </div>
                            <div class="cont-right">
                                <h6>Call Us</h6>
                                <p><a href="tel:+1(21) 234 4567">+1(21) 673 4587</a></p>
                            </div>
                        </div>
                        <div class="d-flex contact-grid mt-4 pt-lg-2">
                            <div class="cont-left text-center me-3">
                                <i class="fas fa-envelope-open-text"></i>
                            </div>
                            <div class="cont-right">
                                <h6>Email Us</h6>
                                <p><a href="mailto:info@eazyschool.com" class="mail">info@eazyschool.com</a></p>
                            </div>
                        </div>
                        <div class="d-flex contact-grid mt-4 pt-lg-2">
                            <div class="cont-left text-center me-3">
                                <i class="fas fa-headphones-alt"></i>
                            </div>
                            <div class="cont-right">
                                <h6>Customer Support</h6>
                                <p><a href="support@eazyschool.com" class="mail">support@eazyschool.com</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- map -->
<div class="map">
    <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d193595.15830921454!2d-74.11976369744556!3d40.69766374856529!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew%20York%2C%20NY%2C%20USA!5e0!3m2!1sen!2sin!4v1639587930884!5m2!1sen!2sin"
            width="100%" height="400" frameborder="0" style="border: 0px;" allowfullscreen=""></iframe>
</div>
<!-- //contact block -->

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->