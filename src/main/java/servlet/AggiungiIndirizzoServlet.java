package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IndirizzoDAO;
import model.Indirizzo;

public class AggiungiIndirizzoServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int idUtente = (int) session.getAttribute("id_utente");

        Indirizzo i = new Indirizzo();
        i.setIdUtente(idUtente);
        i.setIndirizzoRiga1(request.getParameter("riga1"));
        i.setIndirizzoRiga2(request.getParameter("riga2"));
        i.setCitta(request.getParameter("citta"));
        i.setCodicePostale(request.getParameter("cap"));
        i.setNazione(request.getParameter("nazione"));

        try {
            IndirizzoDAO dao = new IndirizzoDAO();
            dao.aggiungiIndirizzo(i);

            response.sendRedirect("indirizzi");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("indirizzi?error=add_fail");
        }
    }
}
