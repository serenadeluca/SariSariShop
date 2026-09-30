package servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.IndirizzoDAO;
import utils.DBConnection;

@SuppressWarnings("unused")
public class EliminaIndirizzoServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idIndirizzo = Integer.parseInt(request.getParameter("id"));

        try {
            IndirizzoDAO dao = new IndirizzoDAO();
            dao.eliminaIndirizzo(idIndirizzo);

            response.sendRedirect(request.getContextPath() + "/indirizzi");


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/indirizzi?error=delete_fail");
        }
    }
}
