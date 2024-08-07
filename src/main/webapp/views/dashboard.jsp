<%@include file="header.jsp" %>
<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Dashboard</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Dashboard</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->
<div class="container py-md-5 py-4">
    <div class="section__content section__content--p30 pt-5">
        <div class="container-fluid">
            <div class="row m-t-25">
                <div class="col-sm-6 col-lg-3">
                    <a href="/display-profile">
                        <div class="overview-item overview-item--c4">
                            <div class="overview__inner">
                                <div class="overview-box clearfix">
                                    <div class="icon">
                                        <i class="fas fa-id-card"></i>
                                    </div>
                                    <div class="text">
                                        <h2>Profile</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-sm-6 col-lg-3">
                        <a href="/admin/messages">
                            <div class="overview-item overview-item--c3">
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="fas fa-envelope-open-text"></i>
                                        </div>
                                        <div class="text">
                                            <h2>Messages</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-sm-6 col-lg-3">
                        <a href="/admin/classes">
                            <div class="overview-item overview-item--c1">
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="fas fa-envelope-open-text"></i>
                                        </div>
                                        <div class="text">
                                            <h2>Classes</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-sm-6 col-lg-3">
                        <a href="/admin/courses">
                            <div class="overview-item overview-item--c2">
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="fas fa-envelope-open-text"></i>
                                        </div>
                                        <div class="text">
                                            <h2>Courses</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_STUDENT')">
                    <div class="col-sm-6 col-lg-3">
                        <a href="/student/courses">
                            <div class="overview-item overview-item--c3">
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="fas fa-envelope-open-text"></i>
                                        </div>
                                        <div class="text">
                                            <h2>Enrolled In</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-sm-6 col-lg-3">
                        <a href="/admin/students">
                            <div class="overview-item overview-item--c3">
                                <div class="overview__inner">
                                    <div class="overview-box clearfix">
                                        <div class="icon">
                                            <i class="fas fa-envelope-open-text"></i>
                                        </div>
                                        <div class="text">
                                            <h2>Records</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>
<!-- footer block -->
<%@ include file="footer.jsp" %>
<!-- //footer block -->