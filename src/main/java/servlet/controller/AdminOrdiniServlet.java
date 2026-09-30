package servlet.controller;

import dao.OrdineDAO;
import model.Ordine;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class AdminOrdiniServlet extends HttpServlet {

    @Resource(name = "jdbc/SariSariPool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            OrdineDAO ordineDAO = new OrdineDAO(dataSource);

            List<Ordine> ordini = ordineDAO.getAllOrdini();
            request.setAttribute("ordini", ordini);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ordini", null);
        }

        request.getRequestDispatcher("/pages/admin/admin-ordini.jsp").forward(request, response);
    }
}
