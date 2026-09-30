const barra_ricerca = document.getElementById("barra_ricerca");
const box_suggerimenti = document.getElementById("suggerimenti");
const tempo_cooldown = 150;
let cooldown;

function visualizzaSuggerimenti() {
    clearTimeout(cooldown);
    const query = barra_ricerca.value.trim();

    if (query.length < 2) {
        box_suggerimenti.style.display = "none";
        return;
    }

    cooldown = setTimeout(() => {
        cercaSuggerimenti(query).then(mostraSuggerimenti);
    }, tempo_cooldown);
}

function mostraSuggerimenti(listaProdotti) {
    box_suggerimenti.innerHTML = "";

    if (!listaProdotti || listaProdotti.length === 0) {
        box_suggerimenti.style.display = "none";
        return;
    }

    listaProdotti.forEach(prodotto => {
        const div = document.createElement("div");
        div.classList.add("item_suggerimento");
        div.innerText = prodotto.nome;

        div.addEventListener("click", () => {
            barra_ricerca.value = prodotto.nome;
            box_suggerimenti.style.display = "none";
            window.location.href = "cerca?q=" + encodeURIComponent(prodotto.nome);
        });

        box_suggerimenti.appendChild(div);
    });

    box_suggerimenti.style.display = "block";
}
