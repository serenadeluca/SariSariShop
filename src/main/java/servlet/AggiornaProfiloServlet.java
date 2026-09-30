package servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utils.DBConnection;

public class AggiornaProfiloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect("login.jsp?error=login_required");
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String numero = request.getParameter("numero");
        String email = request.getParameter("email");

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE utente SET nome = ?, cognome = ?, numero = ?, email = ? "
                       + "WHERE id_utente = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, numero);
            ps.setString(4, email);
            ps.setInt(5, idUtente);

            ps.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/profiloUtente?success=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/profiloUtente?error=update_fail");
        }
    }
}
