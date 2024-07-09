<%@include file="header.jsp" %>
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Students Details</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Students</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<div class="site-section">

    <div class="table-responsive-class">

        <div class="row mb-4">
            <ul>
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
                    </form>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Mobile Num</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <c:if test="${students != null}>">
                    <c:forEach items="students" var="student">
                        <td>${student.name}></td>
                        <td>${student.email}></td>
                        <td>${student.mobileNumber}></td>
                        <td>
                            <a href="/admin/delete-student?userId=${student.userId}" class="btn btn-warning">DELETE</a>
                        </td>
                    </c:forEach>
                </c:if>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-md-2 login-center text-start">
        <a href="/admin/display-classes">
            <button class="btn btn-style btn-style-3 text-left">BACK</button>
        </a>
    </div>
</div>

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->