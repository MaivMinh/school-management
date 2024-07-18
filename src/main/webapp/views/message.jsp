<%@include file="header.jsp" %>
<!-- //header -->

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Contact Messages</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
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
        <table class="table table-striped table-hover dataTable">
            <thead class="table-dark">
            <tr>
                <th class="sorting" scope="col">
                    <a href="/admin/messages?page=${page}&pageSize=10&sortField=name&sortDir=${reverseSortDir}">Name</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/messages?page=${page}&pageSize=10&sortField=mobileNum&sortDir=${reverseSortDir}">Mobile
                        Number</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/messages?page=${page}&pagePage=10&sortField=email&sortDir=${reverseSortDir}">Email</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/messages?page=${page}&pageSize=10&sortField=subject&sortDir=${reverseSortDir}">Subject</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/messages?page=${page}&pageSize=10&sortField=message&sortDir=${reverseSortDir}">Message</a>
                </th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contactMsgs}" var="msg">
                <tr>
                    <td>${msg.name}</td>
                    <td>${msg.mobileNum}</td>
                    <td>${msg.email}</td>
                    <td>${msg.subject}</td>
                    <td>${msg.message}</td>
                    <c:if test="${msg.status.equalsIgnoreCase('open')}">
                        <td>
                            <a href="/admin/close-msg?contact_id=${msg.contactId}" class="btn btn-warning">CLOSE</a>
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
    <%@include file="pagination.jsp"%>
    <div class="col-md-2 login-center text-start">
        <a href="/dashboard">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
    </div>
</div>

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->