<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Nuovi Arrivi</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list_prodotti.css">
</head>



<%@ include file="fragments/header.jsp" %>

<div class="hero-banner fade-in">
        <img src="${pageContext.request.contextPath}/img/hero-banner.jpeg" alt="shop" class="hero-image">
        <div class="hero-overlay">
            <h2>PINOY GOODS AND FOODS</h2>
            <h3>Nuovi Arrivi</h3>
        </div>
    </div>

<!-- ⭐ SIDEBAR FILTRI -->
<%@ include file="filtri.jsp" %>
<div class="overlay" onclick="toggleSidebar()"></div>

<main class="container">

    <!-- ⭐ SEZIONE PRODOTTI ⭐ -->
    <section class="products-section">

        <div class="product-grid">

            <c:forEach items="${nuoviArrivi}" var="p">

                <article class="product-card">

                    <!-- ⭐ IMMAGINE PRODOTTO ⭐ -->
                    <div class="product-image-container">
                        <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                            <img src="${pageContext.request.contextPath}/img/prodotti/${p.immagine}" 
     alt="${p.nome}" class="product-image">
                            
                        </a>
                    </div>

                    <div class="product-info">

                        <!-- ⭐ NOME PRODOTTO ⭐ -->
                        <h4 class="product-name">
                            <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                                ${p.nome}
                            </a>
                        </h4>

                        

                        <!-- ⭐ PREZZO ⭐ -->
                        <p class="product-price">€ ${p.prezzo}</p>

                        <!-- ⭐ DETTAGLI ⭐ -->
                        <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}"
                           class="btn-details">
                            Vedi dettagli
                        </a>

                        <!-- ⭐ AGGIUNGI AL CARRELLO ⭐ -->
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

</main>

<%@ include file="fragments/footer.jsp" %>

</body>
</html>
