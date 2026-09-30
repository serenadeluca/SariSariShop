<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Utente" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestione Utenti</title>
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

    <h1 class="admin-dash-title">Gestione Utenti</h1>

    <table class="admin-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Attivo</th>
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Utente> utenti = (List<Utente>) request.getAttribute("utenti");
            if (utenti != null && !utenti.isEmpty()) {
                for (Utente u : utenti) {
        %>
            <tr>
                <td><%= u.getIdUtente() %></td>
                <td><%= u.getUsername() %></td>
                <td><%= u.getEmail() %></td>
                <td><%= u.isAttivo() ? "Sì" : "No" %></td>
                <td>
                    <a class="admin-btn-small admin-danger"
                       href="${pageContext.request.contextPath}/pages/admin/eliminaUtente?id=<%= u.getIdUtente() %>"
                       onclick="return confirm('Sei sicuro di voler eliminare l\'utente <%= u.getUsername() %>? Verranno eliminati anche i suoi ordini!');">
                        Elimina
                    </a>
                </td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr>
                <td colspan="5" style="text-align: center; padding: 15px;">
                    Nessun utente trovato nel database.
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

</div>

</body>
</html>