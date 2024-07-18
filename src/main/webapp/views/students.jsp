<%@include file="header.jsp" %>
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Students Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/admin/classes">Classes</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>${passioClass.name}</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">
    <div class="table-responsive-md">
        <div class="row mb-4">
            <ul>
                <c:if test="${success != null && success == true}">
                    <li class="alert alert-success">You have added student successfully</li>
                </c:if>
                <c:if test="${error != null && error == true}">
                    <li class="alert alert-danger">Failure to add student into class!</li>
                </c:if>
                <c:if test="${deleted != null && deleted == true}">
                    <li class="alert alert-success">You have deleted student successfully!</li>
                </c:if>
                <c:if test="${deleted != null && deleted == false}">
                    <li class="alert alert-danger">Failure to delete student from class!</li>
                </c:if>
            </ul>
            <div class="overview-wrap">
                <h3 class="heading-21921">${passioClass.name} Students Details</h3>
                <button class="btn btn-style btn-style-3 text-left" type="button"
                        data-bs-toggle="modal" data-bs-target="#addStudent">ADD STUDENT
                </button>
            </div>
        </div>
        <div class="modal fade login-block" id="addStudent" tabindex="-1" role="dialog"
             aria-labelledby="addStudentModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header border-bottom-0">
                        <h5 class="modal-title" id="addStudentModalLabel">Add Student</h5>
                        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="/admin/add-student" method="post" class="signin-form">
                        <input type="text" name="id" value="${passioClass.classId}" hidden/>
                        <div class="modal-body">
                            <div class="input-grids">
                                <label class="control-label" for="email">Student Email </label>
                                <input type="email" class="form-control" name="email" id="email"
                                       required placeholder="Enter Student email">
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
        <table class="table table-striped dataTable table-hover">
            <thead class="table-dark">
            <tr>
                <th class="sorting" scope="col">
                    <a href="/admin/classes/${passioClass.classId}?page=${page}&pageSize=${pageSize}&sortField=${sortField}&sortDir=${reverseSortDir}">
                        Name
                    </a>
                </th>
                <th class="sorting" scope="col">
                    <a href="/admin/classes/${passioClass.classId}?page=${page}&pageSize=${pageSize}&sortField=${sortField}&sortDir=${reverseSortDir}">
                        Email
                    </a>
                </th>
                <th scope="col">Mobile Num</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${students != null}">
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.mobileNum}</td>
                        <td>
                            <form action="/admin/delete-student" method="post">
                                <input type="text" name="userId" value="${student.userId}" hidden>
                                <button type="submit" class="btn btn-warning">DELETE</button>
                                <security:csrfInput/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <%@include file="pagination.jsp"%>
    <div class="col-md-2 login-center text-start">
        <a href="/admin/classes">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
    </div>
</div>

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->