package servlet;

import dao.ProdottoDAO;
import dao.RecensioneDAO;
import model.Prodotto;
import model.Recensione;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DettaglioProdottoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        RecensioneDAO recensioneDAO = new RecensioneDAO();

        Prodotto p = null;

        try {
            p = prodottoDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (p == null) {
            response.sendError(404, "Prodotto non trovato");
            return;
        }

        List<Recensione> recensioni = null;
        try {
            recensioni = recensioneDAO.getRecensioniByProdotto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("prodotto", p);
        request.setAttribute("recensioni", recensioni);

        request.getRequestDispatcher("pages/dettaglioprodotto.jsp").forward(request, response);
    }
}
