<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Recensione" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Recensioni - Admin</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8fafc;
            margin: 0;
            padding: 40px;
            color: #333;
        }

        .container {
            max-width: 1100px;
            margin: 0 auto;
        }

        .header-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        h1 {
            color: #0d9488;
            margin: 0;
            font-size: 2rem;
        }

        .action-buttons {
            display: flex;
            gap: 12px;
        }

        .btn {
            text-decoration: none;
            padding: 10px 18px;
            border-radius: 6px;
            font-weight: 600;
            font-size: 0.9rem;
            transition: background-color 0.2s ease;
        }

        .btn-back {
            background-color: #64748b;
            color: #ffffff;
        }

        .btn-back:hover {
            background-color: #475569;
        }

        .btn-logout {
            background-color: #e11d48;
            color: #ffffff;
        }

        .btn-logout:hover {
            background-color: #be123c;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #ffffff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0,0,0,0.05);
        }

        th, td {
            padding: 14px 18px;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background-color: #0d9488;
            color: #ffffff;
            font-weight: 600;
        }

        tr:hover {
            background-color: #f1f5f9;
        }

        .voto {
            color: #f59e0b;
            font-weight: bold;
        }

        .badge-verified {
            background-color: #d1fae5;
            color: #065f46;
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 0.8rem;
            font-weight: 600;
        }

        .btn-delete {
            color: #e11d48;
            text-decoration: none;
            font-weight: 600;
        }

        .btn-delete:hover {
            text-decoration: underline;
        }

        .empty-msg {
            text-align: center;
            color: #64748b;
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="header-bar">
        <h1>Gestione Recensioni</h1>
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/pages/admin/dashboard" class="btn btn-back">Torna alla Dashboard</a>
            <a href="${pageContext.request.contextPath}/pages/admin/logout" class="btn btn-logout">Logout</a>
        </div>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Prodotto</th>
                <th>Utente</th>
                <th>Titolo & Commento</th>
                <th>Voto</th>
                <th>Acquisto</th>
                <th>Data</th>
                <th>Azione</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioniList");
                if (recensioni != null && !recensioni.isEmpty()) {
                    for (Recensione r : recensioni) {
            %>
            <tr>
                <td><%= r.getIdRecensione() %></td>
                <td>#<%= r.getIdProdotto() %></td>
                <td>#<%= r.getIdUtente() %></td>
                <td>
                    <strong><%= r.getTitolo() %></strong><br>
                    <small><%= r.getCommento() %></small>
                </td>
                <td class="voto"><%= r.getVoto() %> / 5 ★</td>
                <td>
                    <% if (r.isAcquistoVerificato()) { %>
                        <span class="badge-verified">Verificato</span>
                    <% } else { %>
                        <small style="color: #94a3b8;">No</small>
                    <% } %>
                </td>
                <td><%= r.getDataPubblicazione() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/pages/admin/recensioni?action=delete&id=<%= r.getIdRecensione() %>" 
                       class="btn-delete" 
                       onclick="return confirm('Sei sicuro di voler eliminare questa recensione?');">
                       Elimina
                    </a>
                </td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="8" class="empty-msg">Nessuna recensione presente nel database.</td>
            </tr>
            <% } %>
        </tbody>
    </table>

</div>

</body>
</html>