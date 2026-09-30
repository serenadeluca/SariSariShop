<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.DettaglioOrdine" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ordine</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list_prodotti.css">
</head>

<h1>Dettaglio ordine #<%= request.getAttribute("idOrdine") %></h1>

<%
    List<DettaglioOrdine> items = (List<DettaglioOrdine>) request.getAttribute("items");
%>

<table class="tabella-ordini">
    <tr>
        <th>ID Prodotto</th>
        <th>Nome</th>
        <th>Quantità</th>
        <th>Prezzo unitario</th>
        <th>Totale</th>
    </tr>

    <% for (DettaglioOrdine item : items) { %>
        <tr>
            <td><%= item.getIdProdotto() %></td>
            <td><%= item.getNomeProdotto() %></td>
            <td><%= item.getQuantita() %></td>
            <td>€ <%= item.getPrezzoUnitario() %></td>
            <td>€ <%= item.getQuantita() * item.getPrezzoUnitario() %></td>
        </tr>
    <% } %>
</table>


</body>
</html>
