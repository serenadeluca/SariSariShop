package servlet;

import dao.ProdottoDAO;
import model.Prodotto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CercaProdottiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("q");

        // Evita errori se la query è vuota
        if (query == null || query.trim().isEmpty()) {
            request.setAttribute("risultati", new ArrayList<>());
            request.getRequestDispatcher("/pages/risultatiricerca.jsp").forward(request, response);
            return;
        }

        // DAO: cerca prodotti per nome
        ProdottoDAO dao = new ProdottoDAO();
        List<Prodotto> risultati = dao.cercaProdotti(query);

        request.setAttribute("risultati", risultati);
        request.setAttribute("query", query);

        request.getRequestDispatcher("/pages/risultatiricerca.jsp").forward(request, response);
    }
}
