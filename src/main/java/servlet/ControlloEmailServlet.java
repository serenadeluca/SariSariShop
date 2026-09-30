package servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlloEmailServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        elaboraRichiesta(email, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = null;
        try {
            JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
            if (json.has("email")) {
                email = json.get("email").getAsString();
            }
        } catch (Exception e) {
            email = request.getParameter("email");
        }

        elaboraRichiesta(email, response);
    }

    private void elaboraRichiesta(String email, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (email == null || email.trim().isEmpty()) {
            response.getWriter().write("{\"email_esiste\": false, \"esito\": false, \"error\": \"Email mancante\"}");
            return;
        }

        String sql = "SELECT COUNT(*) FROM utente WHERE email = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email.trim());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean esiste = rs.getInt(1) > 0;
                    response.getWriter().write("{\"email_esiste\": " + esiste + ", \"esito\": true}");
                } else {
                    response.getWriter().write("{\"email_esiste\": false, \"esito\": true}");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"email_esiste\": false, \"esito\": false, \"error\": \"Errore database\"}");
        }
    }
}