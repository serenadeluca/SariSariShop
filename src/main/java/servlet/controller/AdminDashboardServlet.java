package servlet.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/pages/admin/admin-dashboard.jsp").forward(request, response);
    }
}
