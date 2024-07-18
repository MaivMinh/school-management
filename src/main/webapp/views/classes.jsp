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
    <c:if test="${existed != null}">
        <ul>
            <c:if test="${existed == true}">
                <li class="alert alert-warning">Class name has already existed. Please choose another name!</li>
            </c:if>
        </ul>
    </c:if>
    <div class="table-responsive-md">
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
        <table class="table table-striped table-hover dataTable">
            <thead class="table-dark">
            <tr>
                <th class="sorting" scope="col">
                    <a href="/admin/classes?page=${page}&pageSize=${pageSize}&sortField=classId&sortDir=${reverseSortDir}">
                        Class ID
                    </a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/classes?page=${page}&pageSize=${pageSize}&sortField=name&sortDir=${reverseSortDir}">
                        Class Name
                    </a>
                </th>
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
                            <a href="/admin/classes/${element.classId}" class="btn btn-success">VIEW</a>
                        </td>
                        <td>
                            <a href="/admin/delete-classes?classId${element.classId}" class="btn btn-warning">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
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
