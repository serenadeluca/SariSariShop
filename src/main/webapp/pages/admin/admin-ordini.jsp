<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordine" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Ordini</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>

<body>

<div class="admin-dashboard">

    <!-- PULSANTE PER TORNARE ALLA DASHBOARD -->
    <div style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/pages/admin/dashboard" class="admin-btn" style="text-decoration: none; display: inline-block;">
            &larr; Torna alla Dashboard
        </a>
    </div>

    <h1 class="admin-dash-title">Gestione Ordini</h1>

    <table class="admin-table">
        <thead>
            <tr>
                <th>ID Ordine</th>
                <th>ID Utente</th>
                <th>Totale</th>
                <th>Stato</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
            if (ordini != null && !ordini.isEmpty()) {
                for (Ordine o : ordini) {
        %>
            <tr>
                <td>#<%= o.getIdOrdine() %></td>
                <td><%= o.getIdUtente() %></td>
                <td>€ <%= String.format("%.2f", o.getTotaleOrdine()) %></td>
                <td>
                    <span class="status-badge"><%= o.getStatoOrdine() %></span>
                </td>
                <td>
                    <a class="admin-btn-small admin-danger"
                       href="${pageContext.request.contextPath}/pages/admin/annullaOrdine?id=<%= o.getIdOrdine() %>"
                       onclick="return confirm('Sei sicuro di voler annullare l\'ordine #<%= o.getIdOrdine() %>?');">
                        🚫 Annulla
                    </a>
                </td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr>
                <td colspan="5" style="text-align: center; padding: 15px;">
                    Nessun ordine presente nel database.
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

</div>

</body>
</html>