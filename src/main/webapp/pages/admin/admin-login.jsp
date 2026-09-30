<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <meta charset="UTF-8">
    <title>Login Admin</title>    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
  
</head>
<body>

<div class="admin-container">
    <div class="admin-card">
        <h2 class="admin-title">Area Admin</h2>

        <!-- Messaggio errore -->
        <!-- <div class="admin-alert error">Credenziali non valide</div> -->

        <form action="${pageContext.request.contextPath}/pages/admin/login" method="post">
            <div class="admin-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <div class="admin-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button class="admin-btn" type="submit">Accedi</button>
        </form>
    </div>
</div>

<p class="register-link" style="text-align: center; margin-top: 15px;">
    Nuovo Amministratore? 
    <a href="${pageContext.request.contextPath}/admin/registrazione" style="color: #11998e; font-weight: bold;">Registrati qui</a>
</p>

</body>
</html>
    