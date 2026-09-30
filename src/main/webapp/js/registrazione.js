console.log("registrazione.js caricato");

document.addEventListener("DOMContentLoaded", () => {

    const campo_username = document.getElementById("username");
    const campo_mail = document.getElementById("email");
    const campo_passw = document.getElementById("password");
    const campo_conferma = document.getElementById("conferma_password");
    const campo_nome = document.getElementById("nome");
    const campo_cognome = document.getElementById("cognome");
    const campo_numero = document.getElementById("numero");

    const errore_mail = document.getElementById("errore_mail");
    const errore_password = document.getElementById("errore_password");
    const errore_conferma = document.getElementById("errore_conferma");
    const errore_numero = document.getElementById("errore_numero");

    const barra_forza = document.getElementById("password-strength");
    const successo = document.getElementById("msg");
    const btn_form_reg = document.querySelector(".btn-registrazione");

    // Usa la variabile globale definita nella JSP o ricava il contextPath
    const contextPath = window.contextPath || "/SariSariShop";

    // PLACEHOLDER NORMALI
    if (campo_mail) campo_mail.placeholder = "example@example.com";
    if (campo_passw) campo_passw.placeholder = "Inserisci una password sicura";
    if (campo_conferma) campo_conferma.placeholder = "Ripeti la password";
    if (campo_numero) campo_numero.placeholder = "Es. 3331234567";

    // Regex email universale
    const regex_mail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // Regex password robusta
    const regex_password = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*?&]{8,}$/;

    // Regex numero telefono (7-15 cifre)
    const regex_numero = /^[0-9]{7,15}$/;

    // -------------------------
    // VALIDAZIONE EMAIL
    // -------------------------
    async function validaEmail() {
        const emailVal = campo_mail.value.trim();
        campo_mail.style.borderColor = "#d0d0d0";

        if (!regex_mail.test(emailVal)) {
            campo_mail.style.borderColor = "#dc3545";
            errore_mail.style.display = "block";
            btn_form_reg.disabled = true;
            return false;
        }

        try {
            const response = await fetch(contextPath + "/controllo_email", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email: emailVal })
            });

            const data = await response.json();

            // 🎯 FIX: La servlet restituisce 'email_esiste', non 'esiste'
            if (!data.email_esiste) {
                campo_mail.style.borderColor = "#28a745";
                errore_mail.style.display = "none";
                btn_form_reg.disabled = false;
                return true;
            } else {
                campo_mail.style.borderColor = "#dc3545";
                errore_mail.style.display = "block";
                errore_mail.textContent = "Email già registrata";
                btn_form_reg.disabled = true;
                return false;
            }

        } catch (error) {
            console.error("Errore controllo email:", error);
            return false;
        }
    }

    // -------------------------
    // BARRA DI FORZA PASSWORD
    // -------------------------
    function aggiornaBarraForza() {
        if (!barra_forza) return;
        const pass = campo_passw.value;

        let forza = 0;
        if (pass.length >= 8) forza++;
        if (/[A-Z]/.test(pass)) forza++;
        if (/[a-z]/.test(pass)) forza++;
        if (/\d/.test(pass)) forza++;
        if (/[@$!%*?&]/.test(pass)) forza++;

        barra_forza.value = forza;
    }

    // -------------------------
    // VALIDAZIONE PASSWORD
    // -------------------------
    function validaPassword() {
        const passVal = campo_passw.value.trim();
        campo_passw.style.borderColor = "#d0d0d0";

        aggiornaBarraForza();

        if (regex_password.test(passVal)) {
            campo_passw.style.borderColor = "#28a745";
            if (errore_password) errore_password.style.display = "none";
        } else {
            campo_passw.style.borderColor = "#dc3545";
            if (errore_password) errore_password.style.display = "block";
            btn_form_reg.disabled = true;
        }

        validaConfermaPassword();
    }

    // -------------------------
    // CONFERMA PASSWORD
    // -------------------------
    function validaConfermaPassword() {
        const pass = campo_passw.value.trim();
        const conferma = campo_conferma.value.trim();

        campo_conferma.style.borderColor = "#d0d0d0";

        if (pass === conferma && pass !== "") {
            campo_conferma.style.borderColor = "#28a745";
            if (errore_conferma) errore_conferma.style.display = "none";
            btn_form_reg.disabled = false;
        } else {
            campo_conferma.style.borderColor = "#dc3545";
            if (errore_conferma) errore_conferma.style.display = "block";
            btn_form_reg.disabled = true;
        }
    }

    // -------------------------
    // VALIDAZIONE NUMERO TELEFONO (OPZIONALE)
    // -------------------------
    function validaNumero() {
        const numeroVal = campo_numero.value.trim();
        campo_numero.style.borderColor = "#d0d0d0";

        // 🎯 FIX: Se il campo è VUOTO, lo accetta (telefono opzionale)
        if (numeroVal === "") {
            if (errore_numero) errore_numero.style.display = "none";
            btn_form_reg.disabled = false;
            return true;
        }

        // Se è compilato, controlla con il RegEx
        if (regex_numero.test(numeroVal)) {
            campo_numero.style.borderColor = "#28a745";
            if (errore_numero) errore_numero.style.display = "none";
            btn_form_reg.disabled = false;
            return true;
        } else {
            campo_numero.style.borderColor = "#dc3545";
            if (errore_numero) errore_numero.style.display = "block";
            btn_form_reg.disabled = true;
            return false;
        }
    }

    // -------------------------
    // REGISTRAZIONE
    // -------------------------
    async function registra() {

        // Controllo finale prima dell'invio
        if (campo_numero.value.trim() !== "" && !regex_numero.test(campo_numero.value.trim())) {
            alert("Il numero di telefono inserito non è valido.");
            return;
        }

        const datiForm = {
            username: campo_username.value.trim(),
            email: campo_mail.value.trim(),
            password: campo_passw.value.trim(),
            nome: campo_nome.value.trim(),
            cognome: campo_cognome.value.trim(),
            numero: campo_numero.value.trim()
        };

        try {
            const response = await fetch(contextPath + "/registrazione", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(datiForm)
            });

            const data = await response.json();

            if (data.esito) {
                successo.classList.remove("errore");
                successo.classList.add("successo");
                successo.textContent = "Registrazione completata con successo!";
                
                document.getElementById("form-registrazione").reset();

                campo_mail.style.borderColor = "#d0d0d0";
                campo_passw.style.borderColor = "#d0d0d0";
                campo_conferma.style.borderColor = "#d0d0d0";
                campo_numero.style.borderColor = "#d0d0d0";
                if (barra_forza) barra_forza.value = 0;

            } else {
                successo.classList.remove("successo");
                successo.classList.add("errore");
                successo.textContent = data.error || "Registrazione fallita";
            }

        } catch (error) {
            console.error(error);
            successo.classList.remove("successo");
            successo.classList.add("errore");
            successo.textContent = "Errore di comunicazione con il server.";
        }
    }

    // LISTENER EVENTI INPUT
    if (campo_mail) campo_mail.addEventListener("input", validaEmail);
    if (campo_passw) campo_passw.addEventListener("input", validaPassword);
    if (campo_conferma) campo_conferma.addEventListener("input", validaConfermaPassword);
    if (campo_numero) campo_numero.addEventListener("input", validaNumero);

    // SUBMIT FORM
    const form = document.getElementById("form-registrazione");
    if (form) {
        form.addEventListener("submit", function(e) {
            e.preventDefault();
            registra();
        });
    }

    console.log("LISTENER REGISTRATO CORRETTAMENTE");
});