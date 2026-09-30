<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dao.IndirizzoDAO, model.Indirizzo" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    IndirizzoDAO dao = new IndirizzoDAO();
    Indirizzo i = dao.getIndirizzoById(id);
%>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>Modifica indirizzo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indirizzi.css">
    
</head>

<body>
<%@ include file="fragments/header.jsp" %>
<h2>Modifica indirizzo</h2>

<form action="${pageContext.request.contextPath}/modificaIndirizzo" method="post" class="form-indirizzo">

    <input type="hidden" name="id" value="<%= i.getIdIndirizzo() %>">

    <label>Indirizzo (riga 1)</label>
    <input type="text" name="riga1" value="<%= i.getIndirizzoRiga1() %>" required>

    <label>Indirizzo (riga 2)</label>
    <input type="text" name="riga2" value="<%= i.getIndirizzoRiga2() %>">

    <label>Città</label>
    <input type="text" name="citta" value="<%= i.getCitta() %>" required>

    <label>CAP</label>
    <input type="text" name="cap" value="<%= i.getCodicePostale() %>" required>

    <label>Nazione</label>
    <input type="text" name="nazione" value="<%= i.getNazione() %>" required>

    <button type="submit" class="btn-save">Salva modifiche</button>
</form>

<a href="${pageContext.request.contextPath}/indirizzi" class="btn-back">← Torna agli indirizzi</a>
<%@ include file="fragments/footer.jsp" %>

</body>
</html>
