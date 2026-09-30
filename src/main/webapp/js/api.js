// ===============================
// API BASE
// ===============================

async function apiPost(url, data = {}) {
    const formData = new FormData();

    for (const key in data) {
        formData.append(key, data[key]);
    }

    const res = await fetch(url, {
        method: "POST",
        body: formData
    });

    const json = await res.json();
    return json;
}

async function apiGet(url) {
    const res = await fetch(url);
    const json = await res.json();
    return json;
}



// ===============================
// LOGIN & REGISTER
// ===============================

function login(email, password) {
    return apiPost("login", { email, password });
}

function register(username, email, password) {
    return apiPost("register", { username, email, password });
}



// ===============================
// INDIRIZZI (CRUD)
// ===============================

function aggiungiIndirizzo(data) {
    return apiPost("aggiungiIndirizzo", data);
}

function modificaIndirizzo(data) {
    return apiPost("modificaIndirizzo", data);
}

function salvaModificaIndirizzo(data) {
    return apiPost("salvaModificaIndirizzo", data);
}

function eliminaIndirizzo(id_indirizzo) {
    return apiPost("eliminaIndirizzo", { id_indirizzo });
}

function usaIndirizzo(id_indirizzo) {
    return apiPost("usaIndirizzo", { id_indirizzo });
}



// ===============================
// ORDINI
// ===============================

function confermaOrdine() {
    return apiPost("confermaOrdine");
}



// ===============================
// ADMIN: PRODOTTI
// ===============================

function adminAddProdotto(data) {
    return apiPost("adminProdotti", { action: "add", ...data });
}

function adminUpdateProdotto(data) {
    return apiPost("adminProdotti", { action: "update", ...data });
}

function adminDeleteProdotto(id_prodotto) {
    return apiPost("adminProdotti", { action: "delete", id_prodotto });
}

function adminListProdotti() {
    return apiPost("adminProdotti", { action: "list" });
}



// ===============================
// ADMIN: CATEGORIE
// ===============================

function adminAddCategoria(nome) {
    return apiPost("adminCategorie", { action: "add", nome });
}

function adminUpdateCategoria(id_categoria, nome) {
    return apiPost("adminCategorie", { action: "update", id_categoria, nome });
}

function adminDeleteCategoria(id_categoria) {
    return apiPost("adminCategorie", { action: "delete", id_categoria });
}

function adminListCategorie() {
    return apiPost("adminCategorie", { action: "list" });
}



// ===============================
// ADMIN: INVENTARIO
// ===============================

function updateStock(id_prodotto, quantita) {
    return apiPost("inventario", { action: "updateStock", id_prodotto, quantita });
}

function getStock(id_prodotto) {
    return apiPost("inventario", { action: "getStock", id_prodotto });
}

function listInventario() {
    return apiPost("inventario", { action: "listInventario" });
}



// ===============================
// SUGGERIMENTI RICERCA (NUOVO)
// ===============================

function cercaSuggerimenti(query) {
    return apiGet("cercaSuggerimenti?q=" + encodeURIComponent(query));
}
