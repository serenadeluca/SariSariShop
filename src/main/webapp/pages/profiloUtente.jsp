<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>Profilo</title>
        <link rel="stylesheet" href="css/profilo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<%@ include file="fragments/header.jsp" %>
<div class="profilo-card">

<h1>Profilo Utente</h1>

<p><strong>Username:</strong> ${username}</p>
<p><strong>Email:</strong> ${email}</p>
<p><strong>Nome:</strong> ${nome}</p>
<p><strong>Cognome:</strong> ${cognome}</p>
<p><strong>Numero:</strong> ${numero}</p>
<p><strong>Data creazione:</strong> ${data_creazione}</p>
<p><strong>Attivo:</strong> ${attivo ? "Sì" : "No"}</p>

<a href="${pageContext.request.contextPath}/pages/aggiornaprofilo.jsp">Modifica profilo</a>
</div>
<%@ include file="fragments/footer.jsp" %>

</body>
</html>
