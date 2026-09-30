<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrazione Admin</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>

<div class="admin-container">
    <div class="admin-card">
        <h2 class="admin-title">Nuovo Amministratore</h2>

        <div id="msg" style="text-align:center; margin-bottom: 15px; font-weight: bold;"></div>

        <form id="form-admin-reg">
            <div class="admin-group">
                <label for="username">Username</label>
                <input type="text" id="username" required>
            </div>

            <div class="admin-group">
                <label for="email">Email</label>
                <input type="email" id="email" required>
            </div>

            <div class="admin-group">
                <label for="password">Password</label>
                <input type="password" id="password" required>
            </div>

            <div class="admin-group">
                <label for="secretKey">Codice Segreto Admin</label>
                <input type="password" id="secretKey" placeholder="Chiave fornita dal sistema" required>
            </div>

            <button class="admin-btn" type="submit">Registra Admin</button>
        </form>

        <p style="text-align: center; margin-top: 15px;">
            Hai già un account? 
            <a href="${pageContext.request.contextPath}/pages/admin/login.jsp" style="color: #11998e; font-weight: bold;">Accedi qui</a>
        </p>
    </div>
</div>

<script>
document.getElementById("form-admin-reg").addEventListener("submit", async function(e) {
    e.preventDefault();
    
    const msg = document.getElementById("msg");
    const payload = {
        username: document.getElementById("username").value.trim(),
        email: document.getElementById("email").value.trim(),
        password: document.getElementById("password").value.trim(),
        secretKey: document.getElementById("secretKey").value.trim()
    };

    try {
        const res = await fetch("${pageContext.request.contextPath}/admin/registrazione", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        
        const data = await res.json();
        
        if (data.esito) {
            msg.style.color = "green";
            msg.textContent = data.message;
            document.getElementById("form-admin-reg").reset();
        } else {
            msg.style.color = "red";
            msg.textContent = data.error;
        }
    } catch(err) {
        msg.style.color = "red";
        msg.textContent = "Errore di connessione al server.";
    }
});
</script>

</body>
</html>