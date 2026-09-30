package servlet;

import dao.ProdottoDAO;

import model.Carrello;
import model.Prodotto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
public class AggiungiCarrelloServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

        ProdottoDAO dao = new ProdottoDAO();
        Prodotto p = null;
		try {
			p = dao.getById(idProdotto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (p != null) {
            carrello.aggiungiProdotto(p, idProdotto);
        }

        response.sendRedirect("pages/carrello.jsp");
    }
}
