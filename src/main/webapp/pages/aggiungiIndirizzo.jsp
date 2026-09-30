<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>Aggiungi indirizzo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indirizzi.css">
    
</head>

<body>
<%@ include file="fragments/header.jsp" %>
<h2>Aggiungi un nuovo indirizzo</h2>

<form action="${pageContext.request.contextPath}/aggiungiIndirizzo" method="post" class="form-indirizzo">

    <label>Indirizzo (riga 1)</label>
    <input type="text" name="riga1" required>

    <label>Indirizzo (riga 2)</label>
    <input type="text" name="riga2">

    <label>Città</label>
    <input type="text" name="citta" required>

    <label>CAP</label>
    <input type="text" name="cap" required>

    <label>Nazione</label>
    <input type="text" name="nazione" required>

    <button type="submit" class="btn-save">Salva indirizzo</button>
</form>

<a href="${pageContext.request.contextPath}/indirizzi" class="btn-back">← Torna agli indirizzi</a>
<%@ include file="fragments/footer.jsp" %>

</body>
</html>
