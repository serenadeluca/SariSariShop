package servlet.controller;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class AdminAnnullaOrdineServlet extends HttpServlet {

    @Resource(name = "jdbc/SariSariPool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE ordine SET stato_ordine = 'annullato' WHERE id_ordine = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/pages/admin/annullaOrdine.jsp").forward(request, response);
    }
}
