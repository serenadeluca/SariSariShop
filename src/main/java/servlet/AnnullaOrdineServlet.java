package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBConnection;

public class AnnullaOrdineServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idOrdine = Integer.parseInt(request.getParameter("id"));

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE ordine SET stato_ordine = 'annullato' WHERE id_ordine = ?"
            );
            ps.setInt(1, idOrdine);
            ps.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/storicoOrdini");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/storicoOrdini?error=annulla_fail");
        }
    }
}
