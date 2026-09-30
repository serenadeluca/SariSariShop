<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Prodotto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Prodotto</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            max-width: 550px;
            margin: 20px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }
        .form-group {
            margin-bottom: 18px;
            display: flex;
            flex-direction: column;
        }
        .form-group label {
            font-weight: 600;
            margin-bottom: 6px;
            color: #2c3e50;
        }
        .form-group input, .form-group textarea, .form-group select {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #cccccc;
            border-radius: 6px;
            font-size: 14px;
            box-sizing: border-box;
        }
        .form-group input:focus, .form-group textarea:focus, .form-group select:focus {
            border-color: #129a7b;
            outline: none;
        }
        .image-preview {
            max-width: 120px;
            max-height: 120px;
            margin-top: 10px;
            border-radius: 6px;
            border: 1px solid #ddd;
            object-fit: cover;
        }
        .btn-submit {
            background-color: #129a7b;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            transition: background 0.2s;
        }
        .btn-submit:hover {
            background-color: #0e7a61;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #555;
            text-decoration: none;
            font-size: 14px;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<div class="form-container">
    <h2 style="text-align: center; color: #129a7b; margin-top: 0; margin-bottom: 25px;">✏️ Modifica Prodotto</h2>

    <%
        Prodotto p = (Prodotto) request.getAttribute("prodotto");
        if (p != null) {
    %>

<form action="${pageContext.request.contextPath}/pages/admin/modificaProdotto" method="post">
        
        <input type="hidden" name="idProdotto" value="<%= p.getIdProdotto() %>">

        <div class="form-group">
            <label for="idCategoria">ID Categoria:</label>
            <input type="number" id="idCategoria" name="idCategoria" value="<%= p.getIdCategoria() != null ? p.getIdCategoria() : "" %>">
        </div>

        <div class="form-group">
            <label for="nome">Nome Prodotto:</label>
            <input type="text" id="nome" name="nome" value="<%= p.getNome() %>" required>
        </div>

        <div class="form-group">
            <label for="urlNomeProdotto">URL Nome Prodotto (Slug):</label>
            <input type="text" id="urlNomeProdotto" name="urlNomeProdotto" value="<%= p.getUrlNomeProdotto() %>" required>
        </div>

        <div class="form-group">
            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4"><%= p.getDescrizione() != null ? p.getDescrizione() : "" %></textarea>
        </div>

        <div class="form-group">
            <label for="prezzo">Prezzo (€):</label>
            <input type="number" step="0.01" id="prezzo" name="prezzo" value="<%= p.getPrezzo() %>" required>
        </div>

        <div class="form-group">
            <label for="quantitaDaTenere">Quantità da tenere (es. 100g, 1L, 50pz):</label>
            <input type="text" id="quantitaDaTenere" name="quantitaDaTenere" value="<%= p.getQuantitaDaTenere() != null ? p.getQuantitaDaTenere() : "" %>">
        </div>

        <div class="form-group">
            <label for="immagine">URL / Percorso Immagine:</label>
            <input type="text" id="immagine" name="immagine" value="<%= p.getImmagine() != null ? p.getImmagine() : "" %>" placeholder="es. prodotti/prodotto.jpg">
            <% if (p.getImmagine() != null && !p.getImmagine().trim().isEmpty()) { %>
                <img src="${pageContext.request.contextPath}/<%= p.getImmagine() %>" class="image-preview" alt="Anteprima" onerror="this.style.display='none';">
            <% } %>
        </div>

        <div class="form-group">
            <label for="pubblico">Pubblico nello shop:</label>
            <select id="pubblico" name="pubblico">
                <option value="true" <%= p.isPubblico() ? "selected" : "" %>>Sì (Visibile)</option>
                <option value="false" <%= !p.isPubblico() ? "selected" : "" %>>No (Nascosto)</option>
            </select>
        </div>

        <button type="submit" class="btn-submit">💾 Salva Modifiche</button>
        <a href="${pageContext.request.contextPath}/pages/admin/prodotti" class="back-link">&larr; Torna ai prodotti</a>
    </form>

    <% } else { %>
        <p style="color: red; text-align: center;">Prodotto non trovato o errore nel caricamento.</p>
        <a href="${pageContext.request.contextPath}/pages/admin/prodotti" class="back-link">&larr; Torna ai prodotti</a>
    <% } %>
</div>
<script>
    document.getElementById("nomeProdotto").addEventListener("input", function() {
        let nome = this.value;
        
        let slug = nome
            .toLowerCase()                         // Converte in minuscolo
            .normalize("NFD")                      // Separa le lettere dai diacritici (accenti)
            .replace(/[\u0300-\u036f]/g, "")      // Rimuove gli accenti
            .replace(/[^a-z0-9 -]/g, "")           // Rimuove caratteri speciali
            .trim()                                // Rimuove spazi a inizio/fine
            .replace(/\s+/g, "-")                  // Sostituisci spazi con trattini
            .replace(/-+/g, "-");                  // Evita trattini doppi

        document.getElementById("urlSlug").value = slug;
    });
</script>
</body>
</html>