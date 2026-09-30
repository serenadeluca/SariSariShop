<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>"${query}"</title>
    <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/risultati.css">
    
    
    
</head>
<body>
  <%@ include file="fragments/header.jsp" %>
<h2>Risultati per: "${query}"</h2>

<c:choose>
    <c:when test="${empty risultati}">
        <p>Nessun prodotto trovato.</p>
    </c:when>

    <c:otherwise>
        <div class="product-grid">
            <c:forEach var="p" items="${risultati}">
                <article class="product-card">

                    <a href="${pageContext.request.contextPath}/dettaglioProdotto?id=${p.idProdotto}">
                        <img src="${pageContext.request.contextPath}/img/prodotti/${p.immagine}" 
                             alt="${p.nome}">
                    </a>

                    <h4>${p.nome}</h4>
                    <p>€ ${p.prezzo}</p>

                </article>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>


    <%@ include file="fragments/footer.jsp" %>

</body>
</html>
