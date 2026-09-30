<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Admin - SariSariShop</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8fafc;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .login-card {
            background-color: #ffffff;
            width: 100%;
            max-width: 400px;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
            position: relative;
            overflow: hidden;
        }

        /* Bordo superiore decorativo verde acqua */
        .login-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 6px;
            background-color: #0d9488;
        }

        .login-card h2 {
            margin: 0 0 8px 0;
            color: #0d9488;
            font-size: 1.8rem;
            text-align: center;
        }

        .login-card p.subtitle {
            margin: 0 0 24px 0;
            color: #64748b;
            font-size: 0.9rem;
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: #334155;
            font-size: 0.9rem;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            font-size: 0.95rem;
            outline: none;
            transition: border-color 0.2s ease;
        }

        .form-group input:focus {
            border-color: #0d9488;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #0d9488;
            color: #ffffff;
            border: none;
            border-radius: 6px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s ease;
            margin-top: 10px;
        }

        .btn-submit:hover {
            background-color: #0f766e;
        }

        .alert-error {
            background-color: #ffe4e6;
            color: #be123c;
            padding: 10px 14px;
            border-radius: 6px;
            font-size: 0.88rem;
            margin-bottom: 20px;
            border: 1px solid #fecdd3;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="login-card">
        <h2>Area Riservata</h2>
        <p class="subtitle">Inserisci le credenziali da amministratore</p>

        <%-- Messaggio di Errore se passato dalla Servlet --%>
        <%
            String errorMsg = (String) request.getAttribute("errorMessage");
            if (errorMsg != null && !errorMsg.isEmpty()) {
        %>
            <div class="alert-error">
                <%= errorMsg %>
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/pages/admin/login" method="post">
            <div class="form-group">
                <label for="username">Username / Email</label>
                <input type="text" id="username" name="username" placeholder="Inserisci username" required autocomplete="off">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Inserisci password" required>
            </div>

            <button type="submit" class="btn-submit">Accedi alla Dashboard</button>
        </form>
    </div>

</body>
</html>