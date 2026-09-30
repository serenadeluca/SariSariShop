package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class OrdineCompletatoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Legge l'id ordine dalla query string
        String idOrdine = request.getParameter("id");

        if (idOrdine == null) {
            // Se manca l'id, rimanda allo storico ordini
            response.sendRedirect(request.getContextPath() + "/storicoOrdini");
            return;
        }

        // Passa l'id alla JSP
        request.setAttribute("idOrdine", idOrdine);

        // Mostra la pagina ordine completato
        request.getRequestDispatcher("/pages/ordineCompletato.jsp").forward(request, response);
    }
}
