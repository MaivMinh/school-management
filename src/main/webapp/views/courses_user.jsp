<%@include file="header.jsp"%>
<!-- //header -->

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Courses</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li><i class="fas fa-angle-right"></i><a href="/dashboard">Dashboard</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Courses</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- courses section -->
<div class="w3l-grids-block-5 py-5">
    <div class="container py-md-5 py-4">
        <div class="title-main text-center mx-auto mb-md-5 mb-4" style="max-width:500px;">
            <h3 class="title-style">Find The Right Course For You</h3>
        </div>
        <div class="row justify-content-center" style="row-gap: 2rem;">
            <c:forEach items="${courses}" var="course">
                <div class="col-lg-4 col-md-6 mt-md-0 mt-4">
                    <div class="coursecard-single" style="height: 500px">
                        <div class="grids5-info position-relative">
                            <img src="${course.img}" alt="" class="img-fluid" style="min-height: 270px" />
                            <div class="meta-list">
                                <a>${course.category}</a>
                            </div>
                        </div>
                        <div class="content-main-top">
                            <div class="content-top mb-4 mt-3">
                                <ul class="list-unstyled d-flex align-items-center justify-content-between">
                                    <li> <i class="fas fa-book-open"></i> ${course.lessons} Lesson</li>
                                    <li> <i class="fas fa-star"></i> ${course.vote != 0 ? course.vote: 5}</li>
                                </ul>
                            </div>
                            <h4><a href="/courses/${course.courseId}">${course.name}</a></h4>
                            <p>${course.introduction}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- //courses section -->

<!-- pagination section -->
<div class="pagination-style text-center mt-5">
    <ul>
        <li>
            <c:if test="${page > 1}">
                <a href="/courses?page=${page - 1}&pageSize=6&sortField=${sortField}&sortDir${sortDir}">
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
                           href="/courses?page=${i}&pageSize=6&sortField${sortField}&sortDir=${sortDir}">${i}</a>
                    </c:if>
                    <c:if test="${page != i}">
                        <a href="/courses?page=${i}&pageSize=6&sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                    </c:if>
                </li>
            </c:forEach>
        </span>
        <li>
            <c:if test="${page < totalPages}">
                <a href="/courses?page=${page + 1}&pageSize=6&&sortField=${sortField}&sortDir=${sortDir}">
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
<!--// pagination section -->

<!-- footer block -->
<%@include file="footer.jsp"%>
<!-- //footer block -->