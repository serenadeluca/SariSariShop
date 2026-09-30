<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>${prodotto.nome}</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <!-- CSS PRINCIPALI -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prodotto.css">
</head>

<body>

<%@ include file="fragments/header.jsp" %>

<div class="product-detail-container">

   <!-- IMMAGINE GRANDE -->
<img src="${pageContext.request.contextPath}/img/prodotti/${prodotto.immagine}" 
     alt="${prodotto.nome}" class="product-image-detail">

   

    <!-- INFO PRODOTTO -->
    <div class="product-info-box">
        <h2>${prodotto.nome}</h2>

        <p>${prodotto.descrizione}</p>

        <p class="product-price">€ ${prodotto.prezzo}</p>

        <!-- BOTTONE AGGIUNGI AL CARRELLO -->
        <a href="${pageContext.request.contextPath}/carrello?action=add&id=${prodotto.idProdotto}&quantita=1"
           class="btn-add-cart"
           id="btn-add-cart">
            Aggiungi al carrello
        </a>
    </div>

    <!-- SLIDER QUANTITÀ -->
    <div class="quantita-container">
        <label for="quantita">Quantità:</label>
        <input type="range" id="quantita" min="1" max="10" value="1" class="quantita-slider">
        <span id="quantita-valore">1</span>
    </div>

</div>

<!-- FORM RECENSIONE -->
<div class="recensione-form soft">
    <h3>Lascia una recensione</h3>

    <form action="inserisciRecensione" method="post">
        <input type="hidden" name="id_prodotto" value="${prodotto.idProdotto}">

        <label>Titolo</label>
        <input type="text" name="titolo" class="soft-input" required>

        <label>Commento</label>
        <textarea name="commento" class="soft-textarea" required></textarea>

        <label>Voto</label>
        <select name="voto" class="soft-select">
            <option value="1">★☆☆☆☆</option>
            <option value="2">★★☆☆☆</option>
            <option value="3">★★★☆☆</option>
            <option value="4">★★★★☆</option>
            <option value="5">★★★★★</option>
        </select>

        <button class="soft-btn" type="submit">Invia recensione</button>
    </form>
</div>

<!-- LISTA RECENSIONI -->
<div class="recensioni-lista">
    <h3 class="recensioni-titolo">Recensioni dei clienti</h3>

    <c:choose>
        <c:when test="${empty recensioni}">
            <p class="no-recensioni">Non ci sono ancora recensioni per questo prodotto.</p>
        </c:when>

        <c:otherwise>
            <c:forEach var="r" items="${recensioni}">
                <div class="recensione-box elegante">

                    <!-- Titolo recensione -->
                    <div class="recensione-header">
                        <h4 class="recensione-titolo">${r.titolo}</h4>

                        <!-- Stelle -->
                        <div class="stelle">
                            <c:forEach begin="1" end="5" var="i">
                                <span class="${i <= r.voto ? 'stella piena' : 'stella vuota'}">★</span>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- Commento -->
                    <p class="recensione-commento">${r.commento}</p>

                    <!-- Footer recensione -->
                    <div class="recensione-footer">

                        <c:if test="${r.acquistoVerificato}">
                            <span class="badge-verificato">✔ Acquisto verificato</span>
                        </c:if>

                        <span class="recensione-data">
                            Pubblicata il 
                            <fmt:formatDate value="${r.dataPubblicazione}" pattern="dd/MM/yyyy" />
                        </span>
                    </div>

                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="fragments/footer.jsp" %>

<!-- JS SLIDER QUANTITÀ -->
<script>
document.addEventListener("DOMContentLoaded", () => {

    const slider = document.getElementById("quantita");
    const valore = document.getElementById("quantita-valore");
    const btn = document.getElementById("btn-add-cart");

    slider.addEventListener("input", () => {
        valore.textContent = slider.value;
    });

    btn.addEventListener("click", () => {
        btn.href = btn.href.replace(/quantita=\d+/, "quantita=" + slider.value);
    });

});
</script>

</body>
</html>
