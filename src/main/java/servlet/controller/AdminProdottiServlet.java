package servlet.controller;

import dao.ProdottoDAO;
import model.Prodotto;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminProdottiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Prodotto> prodotti = ProdottoDAO.getAll();
        request.setAttribute("prodotti", prodotti);

        request.getRequestDispatcher("/pages/admin/admin-prodotti.jsp").forward(request, response);
    }
}
