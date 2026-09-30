<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
</head>
<body>
<div class="search-container">
    <input type="text" id="barra_ricerca" placeholder="Cerca un prodotto..."
           autocomplete="off" oninput="visualizzaSuggerimenti()">

    <div id="suggerimenti" class="suggestions-box"></div>
</div>

<style>
.search-container {
    position: relative;
    width: 300px;
}

#barra_ricerca {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.suggestions-box {
    position: absolute;
    top: 40px;
    width: 100%;
    background: white;
    border: 1px solid #ccc;
    border-radius: 4px;
    display: none;
    max-height: 200px;
    overflow-y: auto;
    z-index: 999;
}

.suggestion-item {
    padding: 10px;
    cursor: pointer;
}

.suggestion-item:hover {
    background: #f0f0f0;
}
</style>

<script>
function visualizzaSuggerimenti() {
    let query = document.getElementById("barra_ricerca").value.trim();

    if (query.length === 0) {
        document.getElementById("suggerimenti").style.display = "none";
        return;
    }

    fetch("${pageContext.request.contextPath}/cercaSuggerimenti?q=" + encodeURIComponent(query))
        .then(response => response.json())
        .then(data => {
            let box = document.getElementById("suggerimenti");
            box.innerHTML = "";

            if (data.length === 0) {
                box.style.display = "none";
                return;
            }

            data.forEach(prod => {
                let item = document.createElement("div");
                item.classList.add("suggestion-item");
                item.textContent = prod.nome;

                item.onclick = () => {
                    window.location.href = "${pageContext.request.contextPath}/cerca?q=" + prod.nome;
                };

                box.appendChild(item);
            });

            box.style.display = "block";
        });
}
</script>

</div>
</body>
</html>