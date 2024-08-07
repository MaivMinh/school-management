<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100&family=Roboto:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/404.css">
    <title>Error Page</title>
</head>
<body>
<section class="page_404">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 ">
                <div class="col-sm-10 col-sm-offset-1  text-center">
                    <div class="four_zero_four_bg">
                    </div>

                    <div class="contant_box_404">
                        <h3 class="h2">
                            Something went wrong!
                        </h3>
                        <c:if test="${message != null}">
                            <p>Error Message:
                                <span style="color: #00c16e; font-weight: bold">${message}</span>
                            </p>
                        </c:if>
                        <c:if test="${message == null}">
                            <p>404 NOT FOUND!</p>
                        </c:if>
                        <a href="/" class="link_404">Go to Home</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>