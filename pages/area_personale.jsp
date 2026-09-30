<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
    <title>Area Personale - Sari Sari Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/area_personale.css">
</head>
<body>
    <%@ include file="fragments/header.jsp" %>

    <main class="container">
        <h1>Benvenuto <strong>${sessionScope.nome_utente}</strong>!</h1>
        <p>Gestisci qui il tuo profilo e i tuoi ordini.</p>

        <div class="dashboard-grid">
            <a href="${pageContext.request.contextPath}/storicoOrdini" class="dash-card"><h3>I miei ordini</h3></a>
            <a href="${pageContext.request.contextPath}/logout" class="dash-card"><h3>Logout</h3></a>
            <a href="${pageContext.request.contextPath}/profiloUtente" class="dash-card"><h3>Info Profilo</h3></a>
            <a href="${pageContext.request.contextPath}/gestione_resi.jsp" class="dash-card"><h3>Gestione resi</h3></a>
            <a href="${pageContext.request.contextPath}/indirizzi" class="dash-card"> Dettagli di spedizione</a>
            <a href="${pageContext.request.contextPath}/contattaci.jsp" class="dash-card"><h3>Contattaci</h3></a>
        </div>
    </main>

    <%@ include file="fragments/footer.jsp" %>
</body>
</html>
