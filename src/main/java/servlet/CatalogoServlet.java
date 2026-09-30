package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdottoDAO;
import model.Prodotto;

public class CatalogoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");

        List<Prodotto> prodotti;

        try {
            if (categoria != null && !categoria.isEmpty()) {
                prodotti = ProdottoDAO.getProdottiByCategoria(categoria);
            } else {
                prodotti = ProdottoDAO.getPubblici();
            }

            request.setAttribute("prodotti", prodotti);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/pages/catalogo.jsp").forward(request, response);
    }
}
