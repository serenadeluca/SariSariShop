<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto - Admin</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon2.ico">
    
    <style>
        body { font-family: sans-serif; background-color: #f8fafc; padding: 40px; }
        .card { background: white; max-width: 500px; margin: 0 auto; padding: 30px; border-radius: 8px; border-top: 5px solid #0d9488; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, textarea { width: 100%; padding: 8px; box-sizing: border-box; }
        button { background: #0d9488; color: white; border: none; padding: 10px 15px; font-size: 16px; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>

<div class="card">
    <h2>➕ Aggiungi Prodotto</h2>

    <form action="${pageContext.request.contextPath}/pages/admin/aggiungiProdotto" method="post">
        
        <div class="form-group">
            <label>ID Categoria:</label>
            <input type="number" name="id_categoria" required>
        </div>

        <div class="form-group">
            <label>Nome Prodotto:</label>
            <input type="text" id="nome" name="nome" required>
        </div>

        <div class="form-group">
            <label>URL Slug (url_nome_prodotto):</label>
            <input type="text" id="url_nome_prodotto" name="url_nome_prodotto" required>
        </div>

        <div class="form-group">
            <label>Descrizione:</label>
            <textarea name="descrizione"></textarea>
        </div>

        <div class="form-group">
            <label>Prezzo (€):</label>
            <input type="text" name="prezzo" required placeholder="1.60">
        </div>

        <div class="form-group">
            <label>Quantità da tenere (es. 10pz, 1L):</label>
            <input type="text" name="quantita_da_tenere">
        </div>

        <div class="form-group">
            <label>URL Immagine:</label>
            <input type="text" name="url_immagine" placeholder="img/prodotti/nome-file.jpg">
        </div>

        <div class="form-group">
            <label>
                <input type="checkbox" name="pubblico" value="1" checked>
                Pubblico nello shop
            </label>
        </div>

        <button type="submit">Salva Prodotto</button>
    </form>
</div>

<script>
    // Generazione automatica dello slug
    document.getElementById("nome").addEventListener("input", function() {
        let slug = this.value.toLowerCase()
            .normalize("NFD").replace(/[\u0300-\u036f]/g, "")
            .replace(/[^a-z0-9 -]/g, "")
            .trim().replace(/\s+/g, "-").replace(/-+/g, "-");
        document.getElementById("url_nome_prodotto").value = slug;
    });
</script>

</body>
</html>