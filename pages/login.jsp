<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Accedi - Sari Sari Shop</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
</head>
<body>
<script>
    window.contextPath = "${pageContext.request.contextPath}";
    window.LOGIN_URL = window.contextPath + "/login";
</script>

    <%@ include file="fragments/header.jsp" %>
 <p class="admin-link">
 <a href="${pageContext.request.contextPath}/pages/admin/login">Sei admin?</a>
 
</p>
    <main class="container login-container">
        <section class="login-form-box">
            <h2>Accedi al tuo account</h2>

            <c:if test="${not empty param.error}">
                <p class="error-message">Devi effettuare il login</p>
            </c:if>

            <form id="loginForm">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" autocomplete="current-password" name="password" required>
                </div>

                <button type="submit" class="btn-cart">Accedi</button>
            </form>

            <p id="error-box" class="error-message" style="display:none;"></p>

            <p class="register-link">
                Non hai un account?
                <a href="${pageContext.request.contextPath}/registrazione">Registrati qui</a>
            </p>
            
          
            
        </section>
         
    </main>

    <%@ include file="fragments/footer.jsp" %>

   
<script src="${pageContext.request.contextPath}/js/login.js?v=2"></script>
   
   
</body>
</html>
