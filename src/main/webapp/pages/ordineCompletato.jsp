<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String idOrdine = request.getParameter("id");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordine Completato</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordinecompletato.css">
</head>

<body>

<%@ include file="fragments/header.jsp" %>

<div class="ordine-box">
    <h1>Ordine completato!</h1>

    <p class="ordine-id">
        Il tuo ordine è stato registrato con successo.<br>
        <strong>Numero ordine: <%= idOrdine %></strong>
    </p>

    <p class="ordine-info">
        Riceverai una email di conferma appena l’ordine verrà elaborato.
    </p>

   <div class="links">
    <a href="fattura?id=<%= idOrdine %>" class="btn">Visualizza Fattura</a>
    <a href="storicoOrdini" class="btn">Vai allo storico ordini</a>
    <a href="catalogo" class="btn">Torna al catalogo</a>
    <a href="home" class="btn">Home</a>
</div>
   
</div>

<%@ include file="fragments/footer.jsp" %>

</body>
</html>
