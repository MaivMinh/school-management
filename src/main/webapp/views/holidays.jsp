<%@include file="header.jsp" %>

<!-- inner banner -->
<section class="inner-banner py-5">
    <div class="w3l-breadcrumb py-lg-5">
        <div class="container pt-4 pb-sm-4">
            <h4 class="inner-text-title pt-5">Holidays</h4>
            <ul class="breadcrumbs-custom-path">
                <li><a href="/home">Home</a></li>
                <li class="active"><i class="fas fa-angle-right"></i>Holidays</li>
            </ul>
        </div>
    </div>
</section>
<!-- //inner banner -->

<!-- Holidays -->
<section class="w3l-timeline-1 py-5">
    <div class="container py-lg-5 py-4">
        <div class="title-heading-w3 text-center mb-sm-5 mb-4">
            <h5 class="title-small">Eazy School</h5>
            <h3 class="title-style">Awesome Holidays</h3>
        </div>
        <div class="row">
            <c:if test="${festival}">
                <div class="col-lg-6">
                    <h5 class="sub-title-timeline"><i class="fas fa-snowman"></i> Festival Holidays</h5>
                    <div class="timeline">
                        <div class="timeline">
                            <div class="column">
                                <c:forEach var="holiday" items="${FESTIVAL}">
                                    <div class="title">
                                        <h2>${holiday.reason}</h2>
                                    </div>
                                    <div class="description">
                                        <h6 class="fas fa-calendar-alt">${holiday.day}<i
                                                class="fas fa-calendar-alt"></i></h6>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${federal}">
                <div class="col-lg-6">
                    <h5 class="sub-title-timeline"><i class="fas fa-snowman"></i> Federal Holidays</h5>
                    <div class="timeline">
                        <div class="timeline">
                            <div class="column">
                                <c:forEach var="holiday" items="${FEDERAL}">
                                    <div class="title">
                                        <h2>${holiday.reason}</h2>
                                    </div>
                                    <div class="description">
                                        <h6 class="fas fa-calendar-alt">${holiday.day}<i
                                                class="fas fa-calendar-alt"></i></h6>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</section>

<!-- // Holidays -->

<!-- footer block -->
<%@include file="footer.jsp" %>
<!-- //footer block -->