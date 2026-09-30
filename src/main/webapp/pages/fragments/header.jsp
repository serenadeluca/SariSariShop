<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Sari Sari Shop</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<!-- ⭐ IMPORTANTE: contextPath nel body -->
<body data-context-path="${pageContext.request.contextPath}">


<header class="main-header">
    <div class="header-top">

        <!-- LOGO -->
        <a href="${pageContext.request.contextPath}/home" class="nav-link">
            <img src="${pageContext.request.contextPath}/img/logo.png" 
                 alt="Sari Sari Shop Logo" class="logo">
        </a>


     <form action="${pageContext.request.contextPath}/cerca" method="GET" class="search-container">
    <input type="text" name="q" id="barra_ricerca"
           placeholder="Cerca un prodotto..."
           autocomplete="off">
    <button type="submit">🔍</button>
</form>

<!-- ⭐ JS della barra -->
<script src="${pageContext.request.contextPath}/js/api.js"></script>
<script src="${pageContext.request.contextPath}/js/barra_di_ricerca.js"></script>
     
      
</div>

       
        <div class="user-actions">

            <c:choose>
                <c:when test="${not empty sessionScope.id_utente}">
                    <a href="${pageContext.request.contextPath}/area_personale" class="action-btn">
                        <img src="${pageContext.request.contextPath}/img/areapersonale.png" 
                             alt="Area personale" class="nav-icon">
                        <span>Area personale</span>
                    </a>
                </c:when>

                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login" class="action-btn">
                        <img src="${pageContext.request.contextPath}/img/user.png" 
                             alt="Icona Accedi" class="nav-icon">
                        <span>Accedi</span>
                    </a>
                </c:otherwise>
            </c:choose>

            <a href="${pageContext.request.contextPath}/carrello" class="action-btn">
                <img src="${pageContext.request.contextPath}/img/cart.png" 
                     alt="Icona Carrello" class="nav-icon">
                <span>Carrello</span>
            </a>
        </div>
    </div>

    <nav class="nav-menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">HOME</a></li>
            <li><a href="${pageContext.request.contextPath}/catalogo">CATALOGO</a></li>
            <li><a href="${pageContext.request.contextPath}/offerte">OFFERTE</a></li>
            <li><a href="${pageContext.request.contextPath}/nuovi-arrivi">NUOVI ARRIVI</a></li>
        </ul>
    </nav>
</header>
</body>
</html>