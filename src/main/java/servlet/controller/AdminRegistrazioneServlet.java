package servlet.controller;

import dao.AmministratoreDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class AdminRegistrazioneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AmministratoreDAO adminDAO;
    
    // 🔑 CODICE SEGRETO PER CREARE UN ADMIN (modificalo a piacere)
    private static final String ADMIN_SECRET_KEY = "SARI_ADMIN_2026";

    @Override
    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/SariSariPool");
            this.adminDAO = new AmministratoreDAO(ds);
        } catch (NamingException e) {
            throw new ServletException("Impossibile trovare la Connection Pool SariSariPool", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/admin/admin-registrazione.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();

            String username = json.has("username") ? json.get("username").getAsString().trim() : "";
            String email = json.has("email") ? json.get("email").getAsString().trim() : "";
            String password = json.has("password") ? json.get("password").getAsString().trim() : "";
            String secretKey = json.has("secretKey") ? json.get("secretKey").getAsString().trim() : "";

            // Verifica Chiave Segreta
            if (!ADMIN_SECRET_KEY.equals(secretKey)) {
                response.getWriter().write("{\"esito\": false, \"error\": \"Codice segreto non valido!\"}");
                return;
            }

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                response.getWriter().write("{\"esito\": false, \"error\": \"Compila tutti i campi obbligatori.\"}");
                return;
            }

            // Livello di accesso predefinito per i nuovi admin: 1
            boolean ok = adminDAO.registra(username, email, password, 1);

            if (ok) {
                response.getWriter().write("{\"esito\": true, \"message\": \"Amministratore registrato con successo!\"}");
            } else {
                response.getWriter().write("{\"esito\": false, \"error\": \"Impossibile registrare l'amministratore\"}");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"esito\": false, \"error\": \"Username o Email già registrati\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"esito\": false, \"error\": \"Richiesta non valida\"}");
        }
    }
}