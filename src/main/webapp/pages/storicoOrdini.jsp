<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.Ordine" %>



<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>Area Personale - Sari Sari Shop</title>
    <link rel="stylesheet" href="css/style.css">
     <link rel="stylesheet" href="css/storicoordini.css">
</head>
<body>
 <%@ include file="fragments/header.jsp" %>
<h1>Storico Ordini</h1>

<%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
%>
<table class="tabella-ordini">
    <tr>
        <th>ID Ordine</th>
        <th>Data</th>
        <th>Totale</th>
        <th>Stato</th>
        <th>Metodo</th>
        <th>Azioni</th>
    </tr>

    <% for (Ordine o : ordini) { %>
        <tr>
            <td><%= o.getIdOrdine() %></td>
            <td><%= o.getDataOrdine() %></td>
            <td>€ <%= o.getTotaleOrdine() %></td>
            <td><%= o.getStatoOrdine() %></td>
            <td><%= o.getMetodoPagamento() %></td>

            <td>
                <a href="${pageContext.request.contextPath}/annullaOrdine?id=<%= o.getIdOrdine() %>" 
                   class="btn-delete"
                   onclick="return confirm('Vuoi annullare questo ordine?');">
                    Annulla
                </a>
            </td>
        </tr>
    <% } %>
</table>


    <%@ include file="fragments/footer.jsp" %>

</body>
</html>
