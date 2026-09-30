package servlet;

import dao.RecensioneDAO;
import model.Recensione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class InserisciRecensioneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Utente non loggato
        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect("login");
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");
        int idProdotto = Integer.parseInt(request.getParameter("id_prodotto"));
        String titolo = request.getParameter("titolo");
        String commento = request.getParameter("commento");
        int voto = Integer.parseInt(request.getParameter("voto"));

        // Acquisto verificato (per ora true)
        boolean verificato = true;

        Recensione r = new Recensione();
        r.setIdProdotto(idProdotto);
        r.setIdUtente(idUtente);
        r.setTitolo(titolo);
        r.setCommento(commento);
        r.setVoto(voto);
        r.setAcquistoVerificato(verificato);

        try {
            RecensioneDAO dao = new RecensioneDAO();
            dao.inserisciRecensione(r);

            response.sendRedirect("dettaglioProdotto?id=" + idProdotto);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errore.jsp");
        }
    }
}
