<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">

</head>
<body>
<body>

<div class="admin-dashboard">
    <h1 class="admin-dash-title">Dashboard Admin</h1>

    <div class="admin-dash-grid">

<a class="admin-card-link" href="${pageContext.request.contextPath}/pages/admin/ordini">
            <div class="admin-dash-card">
                <h2>Gestione Ordini</h2>
                <p>Visualizza, modifica e monitora gli ordini dei clienti.</p>
            </div>
        </a>

<a class="admin-card-link" href="${pageContext.request.contextPath}/pages/admin/prodotti">
            <div class="admin-dash-card">
                <h2>Gestione Prodotti</h2>
                <p>Aggiungi, modifica o rimuovi prodotti dal catalogo.</p>
            </div>
        </a>

<a class="admin-card-link" href="${pageContext.request.contextPath}/pages/admin/utenti">
            <div class="admin-dash-card">
                <h2>Gestione Utenti</h2>
                <p>Controlla gli account e i permessi degli utenti.</p>
            </div>
        </a>


<a class="admin-card-link" href="${pageContext.request.contextPath}/pages/admin/logout">
            <div class="admin-dash-card">
                <h2>Logout</h2>
                <p>Esci</p>
            </div>
        </a>
        
        
<a class="admin-card-link" href="${pageContext.request.contextPath}/pages/admin/recensioni">
            <div class="admin-dash-card">
                <h2>Gestione Recensioni</h2>
                <p>Controlla le recensioni</p>
            </div>
        </a>
    </div>
</div>

</body>

</body>
</html>