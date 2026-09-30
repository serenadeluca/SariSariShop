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

public class CercaSuggerimentiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String query = request.getParameter("q");

        List<Prodotto> risultati = ProdottoDAO.cercaProdotti(query);

        response.setContentType("application/json");
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < risultati.size(); i++) {
            Prodotto p = risultati.get(i);
            json.append("{\"id\":").append(p.getIdProdotto())
                .append(",\"nome\":\"").append(p.getNome()).append("\"}");
            if (i < risultati.size() - 1) json.append(",");
        }

        json.append("]");

        response.getWriter().write(json.toString());
    }
}
