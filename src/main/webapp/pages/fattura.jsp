<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Fattura Ordine #${ordine.idOrdine}</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/fattura.css">
</head>

<body>
    <%@ include file="fragments/header.jsp" %>

<div class="fattura-container">

    <h1>Fattura Ordine #${ordine.idOrdine}</h1>

    <div class="fattura-info">
        <p><strong>Data:</strong> ${ordine.dataOrdine}</p>
        <p><strong>Totale:</strong> € ${ordine.totaleOrdine}</p>
    </div>

    <table class="fattura-tabella">
        <tr>
            <th>Prodotto</th>
            <th>Quantità</th>
            <th>Prezzo</th>
        </tr>

        <c:forEach var="r" items="${righe}">
            <tr>
                <td>${r.nomeProdotto}</td>
                <td>${r.quantita}</td>
                <td>€ ${r.prezzoUnitario}</td>
            </tr>
        </c:forEach>

        <tr class="totale">
            <td colspan="2"><strong>Totale</strong></td>
            <td><strong>€ ${ordine.totaleOrdine}</strong></td>
        </tr>
    </table>
    <button onclick="window.location.href='${pageContext.request.contextPath}/downloadFattura'">
    Scarica PDF
</button>
</div>
    <%@ include file="fragments/footer.jsp" %>

</body>
</html>
