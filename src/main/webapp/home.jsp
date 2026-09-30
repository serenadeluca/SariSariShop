<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sari Sari Shop - Prodotti Autentici delle Filippine</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list_prodotti.css">
</head>

<body data-context-path="${pageContext.request.contextPath}">

    <%@ include file="pages/fragments/header.jsp" %>

    <!-- HERO -->
    <div class="hero-banner">
        <img src="${pageContext.request.contextPath}/img/hero-banner.jpeg" alt="shop" class="hero-image">
        <div class="hero-overlay">
            <h2>PINOY GOODS AND FOODS</h2>
            <h3>Prodotti autentici delle Filippine</h3>
        </div>
    </div>

    <main class="container">

        <!-- ===================== NUOVI ARRIVI ===================== -->
        <section class="products-section">
            <h3 class="section-title">Nuovi Arrivi</h3>

            <div class="product-grid">
                <c:forEach var="p" items="${nuoviArrivi}">
                    <article class="product-card">

                        <div class="product-image-container">
                            <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                                <img src="${pageContext.request.contextPath}/img/prodotti/${p.immagine}" 
     alt="${p.nome}" class="product-image">
                                
                            </a>
                            <span class="badge new">Nuovo</span>
                        </div>

                        <div class="product-info">
                            <h4 class="product-name">
                                <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}" 
                                   style="color: inherit;">
                                    ${p.nome}
                                </a>
                            </h4>

                            <p class="product-price">€ ${p.prezzo}</p>

                            <form action="${pageContext.request.contextPath}/AggiungiCarrelloServlet" method="POST">
                                <input type="hidden" name="idProdotto" value="${p.idProdotto}">
                                <button type="submit" class="btn-cart">Aggiungi al carrello</button>
                            </form>
                        </div>

                    </article>
                </c:forEach>
            </div>
        </section>

        <!-- ===================== PIÙ VENDUTI ===================== -->
        <section class="products-section">
            <h3 class="section-title">I Più Venduti</h3>

            <div class="product-grid">
                <c:forEach var="p" items="${piuVenduti}">
                    <article class="product-card">

                        <div class="product-image-container">
                            <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                                <img src="${pageContext.request.contextPath}/img/prodotti/${p.immagine}" 
     alt="${p.nome}" class="product-image">
                                
                            </a>
                            <span class="badge hot">Hot</span>
                        </div>

                        <div class="product-info">
                            <h4 class="product-name">
                                <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}" 
                                   style="color: inherit;">
                                    ${p.nome}
                                </a>
                            </h4>

                            <p class="product-price">€ ${p.prezzo}</p>

                            <form action="${pageContext.request.contextPath}/AggiungiCarrelloServlet" method="POST">
                                <input type="hidden" name="idProdotto" value="${p.idProdotto}">
                                <button type="submit" class="btn-cart">Aggiungi al carrello</button>
                            </form>
                        </div>

                    </article>
                </c:forEach>
            </div>
        </section>

        <!-- ===================== TUTTI I PRODOTTI ===================== -->
        <section class="products-section">
            <h3 class="section-title">Tutti i Prodotti</h3>

            <div class="product-grid">
                <c:forEach var="p" items="${tuttiProdotti}">
                    <article class="product-card">

                        <div class="product-image-container">
                            <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                               <img src="${pageContext.request.contextPath}/img/prodotti/${p.immagine}" 
     alt="${p.nome}" class="product-image">
                               
                            </a>
                        </div>

                        <div class="product-info">
                            <h4 class="product-name">
                                <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}" 
                                   style="color: inherit;">
                                    ${p.nome}
                                </a>
                            </h4>

                            <p class="product-price">€ ${p.prezzo}</p>

                            <form action="${pageContext.request.contextPath}/AggiungiCarrelloServlet" method="POST">
                                <input type="hidden" name="idProdotto" value="${p.idProdotto}">
                                <button type="submit" class="btn-cart">Aggiungi al carrello</button>
                            </form>
                        </div>

                    </article>
                </c:forEach>
            </div>
        </section>

    </main>

    <%@ include file="pages/fragments/footer.jsp" %>

</body>
</html>
