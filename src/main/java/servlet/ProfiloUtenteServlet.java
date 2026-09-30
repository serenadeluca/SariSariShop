package servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utils.DBConnection;

public class ProfiloUtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect("login.jsp?error=login_required");
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT username, email, nome, cognome, numero, data_creazione, attivo "
                       + "FROM utente WHERE id_utente = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUtente);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("username", rs.getString("username"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("nome", rs.getString("nome"));
                request.setAttribute("cognome", rs.getString("cognome"));
                request.setAttribute("numero", rs.getString("numero"));
                request.setAttribute("data_creazione", rs.getString("data_creazione"));
                request.setAttribute("attivo", rs.getBoolean("attivo"));
            }
            if (request.getParameter("edit") != null) {
                request.getRequestDispatcher("/pages/aggiornaprofilo.jsp").forward(request, response);
                return;
            }


            request.getRequestDispatcher("/pages/profiloUtente.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home.jsp?error=profilo_fail");
        }
    }
}
