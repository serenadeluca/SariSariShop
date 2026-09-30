<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/registrazione.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
</head>
<body>
    <%@ include file="fragments/header.jsp" %>

    <main class="container reg-container">
    <h2>Registrati su Sari Sari Shop</h2>

    <form id="form-registrazione" autocomplete="off">

        <div class="form-group">
            <label>Username</label>
            <input type="text" id="username" required autocomplete="off">
        </div>

        <div class="form-group">
            <label>Nome</label>
            <input type="text" id="nome" required>
        </div>

        <div class="form-group">
            <label>Cognome</label>
            <input type="text" id="cognome" required>
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="email" id="email" required autocomplete="off" placeholder="example@example.com">
            <small id="errore_mail" class="error-message">Email non valida o già esistente</small>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" id="password" required autocomplete="new-password" placeholder="Inserisci una password sicura">
            <small id="errore_password" class="error-message">
                Password troppo debole (min 8 caratteri, 1 maiuscola, 1 numero, 1 simbolo)
            </small>

            <!-- Barra forza password -->
            <progress id="password-strength" value="0" max="5" class="barra-forza"></progress>
        </div>

        <div class="form-group">
            <label>Conferma Password</label>
            <input type="password" id="conferma_password" required autocomplete="new-password" placeholder="Ripeti la password">
            <small id="errore_conferma" class="error-message">Le password non coincidono</small>
        </div>

        <div class="form-group">
            <label>Telefono</label>
            <input type="text" id="numero" placeholder="Es. 3331234567">
            <small id="errore_numero" class="error-message">Numero non valido (7–15 cifre)</small>
        </div>

        <button type="submit" class="btn-registrazione">Registrati</button>
    </form>

    <div id="msg"></div>
</main>
    
    <script src="js/registrazione.js"></script>
    <%@ include file="fragments/footer.jsp" %>
</body>
</html>