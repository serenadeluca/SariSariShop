package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UtenteDAO;
import model.Utente;

public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {

            // Recupero utente loggato PRIMA di invalidare la sessione
            Utente u = (Utente) session.getAttribute("utenteLoggato");

            if (u != null) {
                // Imposto attivo = 0 nel DB
            	UtenteDAO.toggleAttivo(u.getIdUtente(), false);
            }

            // Ora posso invalidare la sessione
            session.invalidate();
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
