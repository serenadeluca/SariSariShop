package servlet;

import dao.ProdottoDAO;
import model.Carrello;
import model.CarrelloItem;
import model.Prodotto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class CarrelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("static-access")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        String action = request.getParameter("action");

        // Aggiungi prodotto
        if ("add".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            try {
                ProdottoDAO dao = new ProdottoDAO();
                Prodotto p = dao.getById(id);

                carrello.aggiungiProdotto(p, quantita);

            } catch (Exception e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/carrello");
            return;
        }

        // Rimozione classica
        if ("remove".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            carrello.rimuoviProdotto(id);

            response.sendRedirect(request.getContextPath() + "/carrello");
            return;
        }

        // Aggiornamento quantità AJAX
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int delta = Integer.parseInt(request.getParameter("delta"));

            for (CarrelloItem item : carrello.getElementi()) {
                if (item.getProdotto().getIdProdotto() == id) {

                    int nuovaQuantita = item.getQuantita() + delta;

                    if (nuovaQuantita <= 0) {
                        carrello.rimuoviProdotto(id);
                    } else {
                        item.aggiungiQuantita(delta);
                    }

                    break;
                }
            }

            response.setContentType("application/json");
            response.getWriter().write("{\"ok\":true}");
            return;
        }

        // Rimozione AJAX
        if ("removeAjax".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            carrello.rimuoviProdotto(id);

            response.setContentType("application/json");
            response.getWriter().write("{\"ok\":true}");
            return;
        }

        // Mostra il carrello
        request.getRequestDispatcher("pages/carrello.jsp").forward(request, response);
    }
}
