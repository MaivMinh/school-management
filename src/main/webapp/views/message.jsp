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
        <table class="table table-striped dataTable">
            <thead class="table-dark">
            <tr>
                <th class="sorting" scope="col">
                    <a href="/admin/display-msg?page=${page}&pageSize=10&sortField=name&sortDir=${reverseSortDir}">Name</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/display-msg?page=${page}&pageSize=10&sortField=mobileNum&sortDir=${reverseSortDir}">Mobile
                        Number</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/display-msg?page=${page}&pagePage=10&sortField=email&sortDir=${reverseSortDir}">Email</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/display-msg?page=${page}&pageSize=10&sortField=subject&sortDir=${reverseSortDir}">Subject</a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/display-msg?page=${page}&pageSize=10&sortField=message&sortDir=${reverseSortDir}">Message</a>
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
    <!-- pagination -->
    <div class="pagination-style text-center mt-5">
        <ul>
            <li>
                <c:if test="${page > 1}">
                    <a href="/admin/display-msg?page=${page - 1}&pageSize=10&sortField=${sortField}&sortDir${sortDir}">
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
                               href="/admin/display-msg?page=${i}&pageSize=10&sortField${sortField}&sortDir=${sortDir}">${i}</a>
                        </c:if>
                        <c:if test="${page != i}">
                            <a href="/admin/display-msg?page=${i}&pageSize=10&sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                        </c:if>
                    </li>
                </c:forEach>
            </span>
            <li>
                <c:if test="${page < totalPages}">
                    <a href="/admin/display-msg?page=${page + 1}&sortField=${sortField}&sortDir=${sortDir}">
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
    <div class="col-md-2 login-center text-start">
        <a href="/dashboard">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
    </div>
</div>

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->