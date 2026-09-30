package servlet;

import dao.IndirizzoDAO;
import model.Indirizzo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class SelezionaIndirizzoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        int idIndirizzo = Integer.parseInt(request.getParameter("id_indirizzo"));

        try {
            IndirizzoDAO dao = new IndirizzoDAO();
            Indirizzo indirizzo = dao.getIndirizzoById(idIndirizzo);

            if (indirizzo != null) {
                session.setAttribute("indirizzo_scelto", idIndirizzo);
            }

            response.sendRedirect("pages/carrello.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("pages/error.jsp");
        }
    }
}
