<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo Prodotti</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list_prodotti.css">
</head>

<body data-context-path="${pageContext.request.contextPath}">


<%@ include file="fragments/header.jsp" %>

<div class="hero-banner fade-in">
        <img src="${pageContext.request.contextPath}/img/hero-banner.jpeg" alt="shop" class="hero-image">
        <div class="hero-overlay">
            <h2>PINOY GOODS AND FOODS</h2>
            <h3>Catalogo</h3>
        </div>
    </div>


<!-- ⭐ SIDEBAR FILTRI -->
<%@ include file="filtri.jsp" %>
<div class="overlay" onclick="toggleSidebar()"></div>

<!-- ⭐ SEZIONE PRODOTTI -->
<section class="products-section">

    <div class="product-grid">

        <c:forEach var="p" items="${prodotti}">

            <article class="product-card">

                <div class="product-image-container">
                    <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                        <img src="${pageContext.request.contextPath}/img/prodotti/${p.immagine}" 
     alt="${p.nome}" class="product-image">
                        
                    </a>
                </div>

                <div class="product-info">

                    <h4 class="product-name">
                        <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                            ${p.nome}
                        </a>
                    </h4>

                    <p class="product-price">€ ${p.prezzo}</p>

                    <form action="${pageContext.request.contextPath}/carrello" method="GET">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="id" value="${p.idProdotto}">
                        <input type="hidden" name="quantita" value="1">

                        <button type="submit" class="btn-cart">Aggiungi al carrello</button>
                    </form>

                </div>

            </article>

        </c:forEach>

    </div>

</section>

<%@ include file="fragments/footer.jsp" %>

</body>
</html>
