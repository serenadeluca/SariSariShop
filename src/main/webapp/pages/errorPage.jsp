<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String test = request.getParameter("test");

    if (test != null) {
        int code = Integer.parseInt(test);
        response.sendError(code);
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Errore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
    
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
    <script>
    let seconds = 5;

    const countdown = setInterval(() => {
        document.getElementById("redirect-info").innerText =
            "Verrai reindirizzato alla Home tra " + seconds + " secondi...";
        seconds--;

        if (seconds < 0) {
            clearInterval(countdown);
            window.location.href = "<%= request.getContextPath() %>/home";
        }
    }, 1000);
</script>
    
</head>

<body data-context-path="${pageContext.request.contextPath}">

    <%@ include file="/pages/fragments/header.jsp" %>

    <div class="error-box">
        <h1 class="error-title">${statusCode}</h1>

        <c:choose>
            <c:when test="${statusCode == 404}">
                <p class="error-message">La pagina che cerchi non esiste.</p>
            </c:when>

            <c:when test="${statusCode == 500}">
                <p class="error-message">Errore interno del server.</p>
            </c:when>

            <c:when test="${statusCode == 403}">
                <p class="error-message">Accesso negato.</p>
            </c:when>

            <c:when test="${statusCode == 400}">
                <p class="error-message">Richiesta non valida.</p>
            </c:when>
        </c:choose>

        <a href="${pageContext.request.contextPath}/home" class="error-btn">Torna alla Home</a>
    </div>
<p id="redirect-info" style="margin-top: 20px; color: #FFD700; font-weight: bold;"></p>

    <%@ include file="/pages/fragments/footer.jsp" %>

</body>
</html>
