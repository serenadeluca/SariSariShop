package servlet;

import dao.ProdottoDAO;
import model.Prodotto;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Nuovi arrivi
            List<Prodotto> nuoviArrivi = ProdottoDAO.getUltimiArrivi(6);
            request.setAttribute("nuoviArrivi", nuoviArrivi);

            // Più venduti
            List<Prodotto> piuVenduti = ProdottoDAO.getPiuVenduti(6);
            request.setAttribute("piuVenduti", piuVenduti);

            // Tutti i prodotti pubblici
            List<Prodotto> tuttiProdotti = ProdottoDAO.getPubblici();
            request.setAttribute("tuttiProdotti", tuttiProdotti);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
