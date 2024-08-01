<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <title>PASSIO School</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100&family=Roboto:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/style-starter.css">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
    <link rel="icon" type="image/x-icon" href="/assets/images/cropped-favicon.webp">
</head>
<body>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- header -->
<header id="site-header" class="fixed-top">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a href="/" class="navbar-brand"><i class="fas fa-graduation-cap"></i>PASSIO</a>

            <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon fa icon-expand fa-bars"></span>
                <span class="navbar-toggler-icon fa icon-close fa-times"></span>
            </button>
            <form class="w-100 me-3 ms-3" role="search" action="/public/search" method="post" >
                <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                <security:csrfInput/>
            </form>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll">
                    <li class="nav-item">
                        <a href="/" class="nav-link active" aria-current="page">Home</a>
                    </li>
                    <li class="nav-item">
                        <a href="/courses" class="nav-link">Courses</a>
                    </li>
                    <li class="nav-item">
                        <a href="/contact" class="nav-link">Contact</a>
                    </li>
                    <security:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a href="/dashboard" class="nav-link">Dashboard</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="isAnonymous()">
                        <li class="nav-item">
                            <a href="/public/login" class="nav-link">Login</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <%--    Spring Security's LogoutFilter sẽ block GET Method /logout. Nó chỉ xử lý POST Method /logout.--%>
                            <form action="/logout" method="POST">
                                <button type="submit" class="nav-link">Logout</button>
                                    <%--    Tạo csrf token giúp tăng tính bảo mật cho endpoint này, vì khi đó cần phải có JSESSIONID + csrf token mới có thể đăng xuất thành công.--%>
                                <security:csrfInput/>
                            </form>
                        </li>
                    </security:authorize>
                </ul>
            </div>
            <!-- toggle switch for light and dark theme -->
            <div class="cont-ser-position">
                <nav class="navigation">
                    <div class="theme-switch-wrapper">
                        <label class="theme-switch" for="checkbox">
                            <input type="checkbox" id="checkbox">
                            <div class="mode-container">
                                <i class="gg-sun"></i>
                                <i class="gg-moon"></i>
                            </div>
                        </label>
                    </div>
                </nav>
            </div>
            <!-- //toggle switch for light and dark theme -->
        </nav>
    </div>
</header>
<!-- //header -->