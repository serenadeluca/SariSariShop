<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Categorie</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">

    <style>
        /* ================================
           SEZIONE CATEGORIE — VERSIONE 3
           ================================ */
        .categorie-section {
            background: linear-gradient(135deg, #6a0dad, #8a2be2);
            padding: 30px;
            border-radius: 12px;
            margin: 30px auto;
            width: 90%;
            max-width: 1100px;
            color: #7fffd4; /* Verde acqua */
            text-align: center;
        }

        .categorie-section h2 {
            font-size: 28px;
            margin-bottom: 20px;
            letter-spacing: 1px;
        }

        .categorie-list {
            list-style: none;
            padding: 0;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 12px;
        }

        .categorie-list li {
            background: rgba(127, 255, 212, 0.15);
            color: #7fffd4;
            padding: 10px 18px;
            border-radius: 8px;
            font-size: 16px;
            transition: 0.3s;
            cursor: pointer;
        }

        .categorie-list li:hover {
            background: rgba(127, 255, 212, 0.3);
            transform: translateY(-3px);
        }
        
        .overlay {
    display: none;
}

.sidebar-open .overlay {
    display: block;
    pointer-events: auto;
}
        
    </style>

</head>
<body>

    <div class="categorie-section">
        <h2>Categorie</h2>

       <ul class="categorie-list">
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=bevande">Bevande</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=congelato">Congelato</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=conserve">Conserve</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=cura-personale">Cura Personale</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=dolci-colazione">Dolci e Colazione</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=latte-caffe">Latte e Caffè</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=legumi-farine">Legumi e Farine</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=non-food">Non Food</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=noodles-riso">Noodles e Riso</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=salse-condimenti">Salse e Condimenti</a></li>
<li><a href="${pageContext.request.contextPath}/catalogo?categoria=snack">Snack</a></li>

    
</ul>
       
    </div>

</body>
</html>
