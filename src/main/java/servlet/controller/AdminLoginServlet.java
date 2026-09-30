package servlet.controller;

import dao.AmministratoreDAO;
import model.Amministratore;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginServlet extends HttpServlet {

    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/SariSariPool");
        } catch (NamingException e) {
            throw new ServletException("Errore DataSource", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/pages/admin/admin-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AmministratoreDAO dao = new AmministratoreDAO(dataSource);
        Amministratore admin = null;

        try {
            admin = dao.login(email, password);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect(request.getContextPath() + "/pages/admin/dashboard");
        } else {
            request.setAttribute("error", "Credenziali admin non valide");
            request.getRequestDispatcher("/pages/admin/admin-login.jsp").forward(request, response);
        }
    }
}
