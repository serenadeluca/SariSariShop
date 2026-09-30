
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.IndirizzoDAO, model.Indirizzo, java.util.List" %>

<%
int idUtente = Integer.valueOf(String.valueOf(session.getAttribute("id_utente")));
    IndirizzoDAO dao = new IndirizzoDAO();
    List<Indirizzo> indirizzi = dao.getIndirizziByUtente(idUtente);
%>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>CARRELLO</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrello.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indirizzi.css">
    
</head>

<body data-context-path="${pageContext.request.contextPath}">


<%@ include file="fragments/header.jsp" %>
<h2>Seleziona indirizzo di spedizione</h2>

<% for (Indirizzo i : indirizzi) { %>

    <div class="indirizzo-box">
        <p><strong><%= i.getIndirizzoRiga1() %></strong></p>
        <p><%= i.getCitta() %> - <%= i.getCodicePostale() %></p>
        <p><%= i.getNazione() %></p>

        <form action="${pageContext.request.contextPath}/selezionaIndirizzo" method="post">
            <input type="hidden" name="id_indirizzo" value="<%= i.getIdIndirizzo() %>">
            <button type="submit">Usa questo indirizzo</button>
        </form>
    </div>

<% } %>

<%@ include file="fragments/footer.jsp" %>

</body>
</html>

