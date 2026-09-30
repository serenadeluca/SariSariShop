<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, model.Indirizzo" %>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>I tuoi indirizzi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indirizzi.css">
    
</head>

<body>
<%@ include file="fragments/header.jsp" %>
<h2>I tuoi indirizzi</h2>

<a href="${pageContext.request.contextPath}/pages/aggiungiIndirizzo.jsp" class="btn-add">
    + Aggiungi nuovo indirizzo
</a>

<%
    List<Indirizzo> indirizzi = (List<Indirizzo>) request.getAttribute("indirizzi");
%>

<% if (indirizzi == null || indirizzi.isEmpty()) { %>

    <p>Non hai ancora salvato indirizzi.</p>

<% } else { %>

    <table class="indirizzi-table">
        <thead>
            <tr>
                <th>Indirizzo</th>
                <th>Città</th>
                <th>CAP</th>
                <th>Nazione</th>
                <th>Azioni</th>
            </tr>
        </thead>

        <tbody>
        <% for (Indirizzo i : indirizzi) { %>
            <tr>
                <td> ID: <%= i.getIdIndirizzo() %><br> <%= i.getIndirizzoRiga1() %> <br> <%= i.getIndirizzoRiga2() %></td>
                <td><%= i.getCitta() %></td>
                <td><%= i.getCodicePostale() %></td>
                <td><%= i.getNazione() %></td>

                <td>
                    <a href="${pageContext.request.contextPath}/pages/modificaIndirizzo.jsp?id=<%= i.getIdIndirizzo() %>" class="btn-edit">
                        Modifica
                    </a>

                    <a href="${pageContext.request.contextPath}/eliminaIndirizzo?id=<%= i.getIdIndirizzo() %>" 
                       class="btn-delete"
                       onclick="return confirm('Sei sicura di voler eliminare questo indirizzo?');">
                        Elimina
                    </a>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

<% } %>
<%@ include file="fragments/footer.jsp" %>

</body>
</html>
