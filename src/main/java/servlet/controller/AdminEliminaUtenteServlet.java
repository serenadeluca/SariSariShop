package servlet.controller;

import dao.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminEliminaUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        UtenteDAO.delete(id);

        request.getRequestDispatcher("/pages/admin/eliminaUtente.jsp").forward(request, response);
    }
}
