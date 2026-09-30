package servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.UtenteDAO;
import model.Utente;

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
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            this.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/SariSariPool");
        } catch (NamingException e) {
            throw new ServletException("Impossibile trovare la Connection Pool", e);
        }
    }

    // -------------------- GET: mostra la pagina login --------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
        dispatcher.forward(request, response);
    }
    // ---------------------------------------------------------------------

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject json;
        try {
            json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().write("{\"esito\": false, \"error\": \"JSON non valido\"}");
            return;
        }

        String email = json.get("email").getAsString();
        String password = json.get("password").getAsString();

        String query = "SELECT id_utente, username, nome FROM utente "
                     + "WHERE email = ? AND password_hash = SHA2(?, 256)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    UtenteDAO.toggleAttivo(rs.getInt("id_utente"), true);

                 // Recupero l'utente completo dal DB
                    Utente u = UtenteDAO.getById(rs.getInt("id_utente"));

                    // Salvo l'oggetto Utente nella sessione
                    session.setAttribute("utenteLoggato", u);
                    session.setAttribute("id_utente", u.getIdUtente());


                    // Imposto attivo = 1 nel DB
                    UtenteDAO.toggleAttivo(u.getIdUtente(), true);

                    response.getWriter().write("{\"esito\": true}");
                } else {
                    response.getWriter().write("{\"esito\": false, \"error\": \"Credenziali errate\"}");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"esito\": false, \"error\": \"Errore database\"}");
        }
    }
}
