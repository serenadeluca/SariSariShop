<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Prodotti</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>

<body>

<div class="admin-dashboard">

    <!-- BARRA DI NAVIGAZIONE IN ALTO -->
    <div style="margin-bottom: 20px; display: flex; gap: 10px;">
        <a href="${pageContext.request.contextPath}/pages/admin/dashboard" class="admin-btn" style="text-decoration: none;">
            &larr; Torna alla Dashboard
        </a>
        <a class="admin-btn" href="${pageContext.request.contextPath}/pages/admin/aggiungiProdotto.jsp" style="text-decoration: none;">
            ➕ Aggiungi nuovo prodotto
        </a>
    </div>

    <h1 class="admin-dash-title">Gestione Prodotti</h1>

    <table class="admin-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Prezzo</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
            if (prodotti != null && !prodotti.isEmpty()) {
                for (Prodotto p : prodotti) {
        %>
            <tr>
                <td><%= p.getIdProdotto() %></td>
                <td><%= p.getNome() %></td>
                <td>€ <%= String.format("%.2f", p.getPrezzo()) %></td>
                <td>
                    <a class="admin-btn-small"
                       href="${pageContext.request.contextPath}/pages/admin/modificaProdotto?id=<%= p.getIdProdotto() %>">
                        ✏️ Modifica
                    </a>

                    <a class="admin-btn-small admin-danger"
                       href="${pageContext.request.contextPath}/pages/admin/eliminaProdotto?id=<%= p.getIdProdotto() %>"
                       onclick="return confirm('Sei sicuro di voler eliminare il prodotto <%= p.getNome() %>?');">
                        🗑️ Elimina
                    </a>
                </td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr>
                <td colspan="4" style="text-align: center; padding: 15px;">
                    Nessun prodotto trovato nel database.
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

</div>

</body>
</html>