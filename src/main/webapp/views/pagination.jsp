<!-- pagination -->
    <div class="pagination-style text-center mt-5">
        <ul>
            <li>
                <c:if test="${page > 1}">
                    <a href="/admin/messages?page=${page - 1}&pageSize=10&sortField=${sortField}&sortDir${sortDir}">
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
                               href="/admin/messages?page=${i}&pageSize=10&sortField${sortField}&sortDir=${sortDir}">${i}</a>
                        </c:if>
                        <c:if test="${page != i}">
                            <a href="/admin/messages?page=${i}&pageSize=10&sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                        </c:if>
                    </li>
                </c:forEach>
            </span>
            <li>
                <c:if test="${page < totalPages}">
                    <a href="/admin/messages?page=${page + 1}&sortField=${sortField}&sortDir=${sortDir}">
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