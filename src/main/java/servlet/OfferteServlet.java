package servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdottoDAO;
import model.Prodotto;

public class OfferteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");

        List<Prodotto> offerte;

        try {

            if (categoria != null && !categoria.isEmpty()) {

                // ⭐ OFFERTA PER CATEGORIA (prezzo < 10)
                offerte = ProdottoDAO.getProdottiByCategoria(categoria)
                        .stream()
                        .filter(p -> p.getPrezzo() < 10)
                        .collect(Collectors.toList());

            } else {

                // ⭐ TUTTI I PRODOTTI IN OFFERTA (prezzo < 10)
                offerte = ProdottoDAO.getAll()
                        .stream()
                        .filter(p -> p.getPrezzo() < 10)
                        .collect(Collectors.toList());
            }

            request.setAttribute("prodottiInOfferta", offerte);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/pages/offerte.jsp").forward(request, response);
    }
}
