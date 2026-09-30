<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica Profilo</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profilo.css">
    

</head>
<body>
<%@ include file="fragments/header.jsp" %>
<div class="profilo-card">

<h1>Modifica Profilo</h1>

<form action="${pageContext.request.contextPath}/aggiornaprofilo" method="post">

    <label>Nome:</label>
    <input type="text" name="nome" value="${nome}" required>

    <label>Cognome:</label>
    <input type="text" name="cognome" value="${cognome}" required>

    <label>Email:</label>
    <input type="email" name="email" value="${email}" required>

    <label>Numero:</label>
    <input type="text" name="numero" value="${numero}" required>

    <button type="submit">Salva modifiche</button>
</form>
</div>

<%@ include file="fragments/footer.jsp" %>

</body>
</html>