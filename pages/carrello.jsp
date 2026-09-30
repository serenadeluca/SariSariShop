<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>CARRELLO</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrello.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body data-context-path="${pageContext.request.contextPath}">
<script src="${pageContext.request.contextPath}/js/carrello.js"></script>

<%@ include file="fragments/header.jsp" %>

<div class="carrello-container">

    <div class="carrello-title">
        <div class="barra-gialla"></div>
        <h2>Il tuo carrello</h2>
    </div>

    <c:choose>

        <c:when test="${empty sessionScope.carrello.elementi}">
            <p>Il carrello è vuoto.</p>
        </c:when>

        <c:otherwise>

            <table class="carrello-table">
                <thead>
                <tr>
                    <th>Prodotto</th>
                    <th>Prezzo</th>
                    <th>Quantità</th>
                    <th>Totale</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${sessionScope.carrello.elementi}" var="item">
                    <tr>
                        <td data-label="Prodotto">${item.prodotto.nome}</td>

                        <td data-label="Prezzo">€ ${item.prodotto.prezzo}</td>

                        <td data-label="Quantità">
    <button type="button" class="qty-btn" onclick="updateQty(${item.prodotto.idProdotto}, -1)">−</button>
    <span class="qty-value" id="qty-${item.prodotto.idProdotto}">${item.quantita}</span>
    <button type="button" class="qty-btn" onclick="updateQty(${item.prodotto.idProdotto}, 1)">+</button>
</td>

<td data-label="Azione">
    <button type="button" class="remove-btn" onclick="removeItem(${item.prodotto.idProdotto})">Rimuovi</button>
</td>
                        
                        <td data-label="Totale">€ ${item.subTotale}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="carrello-totale">
                Totale: € ${sessionScope.carrello.totale}
            </div>

<a href="${pageContext.request.contextPath}/pages/selezionaIndirizzo.jsp" class="btn-indirizzo">
    Seleziona indirizzo di spedizione
</a>

<button class="checkout-btn" onclick="confermaOrdine()">CONFERMA ORDINE</button>

        </c:otherwise>

    </c:choose>

</div>

<%@ include file="fragments/footer.jsp" %>

<script>
function confermaOrdine() {
    fetch("${pageContext.request.contextPath}/confermaOrdine", {
        method: "POST"
    })
    .then(r => r.json())
    .then(data => {
        if (data.success) {
            alert("Ordine confermato!");
            window.location.href = "${pageContext.request.contextPath}/ordineCompletato?id=" + data.id_ordine;
        } else {
            alert(data.error);
        }
    });
}


</script>

</body>
</html>
