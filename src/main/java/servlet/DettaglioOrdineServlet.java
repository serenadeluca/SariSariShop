package servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.OrdineDAO;
import model.DettaglioOrdine;
@SuppressWarnings("unused")
public class DettaglioOrdineServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/SariSariPool");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int idOrdine = Integer.parseInt(req.getParameter("id"));

        DettaglioOrdine dao = new DettaglioOrdine(dataSource);

        try {
            req.setAttribute("idOrdine", idOrdine);
            req.setAttribute("items", dao.getDettagli(idOrdine));
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/pages/dettaglioOrdine.jsp").forward(req, resp);
    }
}
