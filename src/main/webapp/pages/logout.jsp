<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
    <title>Area Personale - Sari Sari Shop</title>
    <link rel="stylesheet" href="css/style.css">
    </head>
<body>
    <%@ include file="fragments/header.jsp" %>
     <main class="container">
        <h1>Impostazioni accesso <strong>${sessionScope.nome_utente}</strong>!</h1>
        
<%
    // Invalida la sessione se esiste
    HttpSession sessione = request.getSession(false);
    if (sessione != null) {
        sessione.invalidate();
    }

    // Redirect alla home tramite servlet /home
    response.sendRedirect(request.getContextPath() + "/home");
%>
 <%@ include file="fragments/footer.jsp" %>
</body>
</html>
