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

public class NuoviArriviServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");

        List<Prodotto> nuoviArrivi;

        try {
            if (categoria != null && !categoria.isEmpty()) {
                nuoviArrivi = ProdottoDAO.getNuoviArriviByCategoria(categoria);
            } else {
                nuoviArrivi = ProdottoDAO.getUltimiArrivi(6);
            }

            request.setAttribute("nuoviArrivi", nuoviArrivi);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/pages/nuovi-arrivi.jsp").forward(request, response);
    }
}
