document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("loginForm");
    const errorBox = document.getElementById("error-box");

    form.addEventListener("submit", async function (e) {
        e.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!email || !password) {
            errorBox.style.display = "block";
            errorBox.textContent = "Inserisci email e password";
            return;
        }

        try {
            const response = await fetch(LOGIN_URL, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            const result = await response.json();

            if (result.esito === true) {
                window.location.href = `${contextPath}/home`;
            } else {
                errorBox.style.display = "block";
                errorBox.textContent = result.error || "Credenziali non valide";
            }

        } catch (err) {
            console.error("Errore nella richiesta:", err);
            errorBox.style.display = "block";
            errorBox.textContent = "Errore di connessione al server";
        }
    });
});
