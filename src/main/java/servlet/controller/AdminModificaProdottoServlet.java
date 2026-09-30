package servlet.controller;

import dao.ProdottoDAO;
import model.Prodotto;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminModificaProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Prodotto p = ProdottoDAO.getById(id);

        request.setAttribute("prodotto", p);
        request.getRequestDispatcher("/pages/admin/modificaProdotto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Prodotto p = new Prodotto();

        // I NOMI DEVONO CORRISPONDERE ALLA JSP
        p.setIdProdotto(Integer.parseInt(request.getParameter("idProdotto")));

        String idCat = request.getParameter("idCategoria");
        if (idCat != null && !idCat.isEmpty()) {
            p.setIdCategoria(Integer.parseInt(idCat));
        }

        p.setNome(request.getParameter("nome"));
        p.setUrlNomeProdotto(request.getParameter("urlNomeProdotto"));
        p.setDescrizione(request.getParameter("descrizione"));
        p.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        p.setQuantitaDaTenere(request.getParameter("quantitaDaTenere"));
        p.setImmagine(request.getParameter("immagine"));

        // pubblico = true/false
        p.setPubblico(Boolean.parseBoolean(request.getParameter("pubblico")));

        // AGGIORNA NEL DB
        ProdottoDAO.update(p);

        // REDIRECT ALLA PAGINA PRODOTTI
        response.sendRedirect(request.getContextPath() + "/pages/admin/prodotti");
    }
}
