package servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrazioneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            this.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/SariSariPool");
        } catch (NamingException e) {
            throw new ServletException("Impossibile trovare la Connection Pool SariSariPool", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/registrazione.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();

            String username = json.has("username") ? json.get("username").getAsString() : "";
            String email = json.has("email") ? json.get("email").getAsString() : "";
            String password = json.has("password") ? json.get("password").getAsString() : "";
            String nome = json.has("nome") ? json.get("nome").getAsString() : "";
            String cognome = json.has("cognome") ? json.get("cognome").getAsString() : "";
            
            // Lettura del telefono / numero
            String numero = "";
            if (json.has("numero")) {
                numero = json.get("numero").getAsString();
            } else if (json.has("telefono")) {
                numero = json.get("telefono").getAsString();
            }

            String query = "INSERT INTO utente "
                         + "(username, email, password_hash, nome, cognome, numero, data_creazione, attivo) "
                         + "VALUES (?, ?, SHA2(?, 256), ?, ?, ?, NOW(), 1)";

            try (Connection con = dataSource.getConnection();
                 PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, nome);
                stmt.setString(5, cognome);
                
                // Se il numero è vuoto, invia NULL per non far fallire la query
                if (numero == null || numero.trim().isEmpty()) {
                    stmt.setNull(6, java.sql.Types.VARCHAR);
                } else {
                    stmt.setString(6, numero.trim());
                }

                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    response.getWriter().write("{\"esito\": true, \"message\": \"Registrazione completata\"}");
                } else {
                    response.getWriter().write("{\"esito\": false, \"error\": \"Registrazione fallita\"}");
                }

            } catch (SQLException e) {
                System.err.println("❌ ERRORE SQL REGISTRAZIONE: " + e.getMessage());
                e.printStackTrace();
                response.getWriter().write("{\"esito\": false, \"error\": \"Errore database: " + e.getMessage().replace("\"", "'") + "\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"esito\": false, \"error\": \"Dati del form non validi\"}");
        }
    }
}