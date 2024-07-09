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