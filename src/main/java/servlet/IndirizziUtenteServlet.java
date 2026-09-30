package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IndirizzoDAO;
import model.Indirizzo;

public class IndirizziUtenteServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");

        try {
            IndirizzoDAO dao = new IndirizzoDAO();
            List<Indirizzo> indirizzi = dao.getIndirizziByUtente(idUtente);

            request.setAttribute("indirizzi", indirizzi);
            request.getRequestDispatcher("pages/indirizzi.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home.jsp?error=indirizzi_fail");
        }
    }
}
