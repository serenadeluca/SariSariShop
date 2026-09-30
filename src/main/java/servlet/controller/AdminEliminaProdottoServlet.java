package servlet.controller;

import dao.ProdottoDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminEliminaProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        ProdottoDAO.delete(id);

        request.getRequestDispatcher("/pages/admin/eliminaProdotto.jsp").forward(request, response);
    }
}
