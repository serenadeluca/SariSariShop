// ===============================
//  CARRELLO - SARI SARI SHOP
// ===============================

// Context path passato dalla JSP
const contextPath = document.body.getAttribute("data-context-path");

// -------------------------------
// Aggiorna quantità (+ / -)
// -------------------------------
function updateQty(id, delta) {
    fetch(`${contextPath}/carrello?action=update&id=${id}&delta=${delta}`)
        .then(r => r.json())
        .then(data => {
            if (data.ok) {
                location.reload();
            } else {
                alert("Errore nell'aggiornamento della quantità");
            }
        })
        .catch(() => alert("Errore di comunicazione col server"));
}

// -------------------------------
// Rimuovi prodotto dal carrello
// -------------------------------
function removeItem(id) {
    if (!confirm("Vuoi davvero rimuovere questo prodotto?")) return;

    fetch(`${contextPath}/carrello?action=removeAjax&id=${id}`)
        .then(r => r.json())
        .then(data => {
            if (data.ok) {
                alert("Prodotto rimosso dal carrello");
                location.reload();
            } else {
                alert("Errore nella rimozione del prodotto");
            }
        })
        .catch(() => alert("Errore di comunicazione col server"));
}

// -------------------------------
// Conferma ordine
// -------------------------------
function confermaOrdine() {
    if (!confirm("Vuoi confermare l'ordine?")) return;

    fetch(`${contextPath}/confermaOrdine`, {
        method: "POST"
    })
    .then(r => r.json())
    .then(data => {
        if (data.success) {
            alert("Ordine confermato!");
            window.location.href = `${contextPath}/ordineCompletato.jsp?id=${data.id_ordine}`;
        } else {
            alert(data.error);
        }
    })
    .catch(() => alert("Errore di comunicazione col server"));
}
