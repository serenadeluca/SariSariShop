package servlet.controller;

import dao.UtenteDAO;
import model.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminUtentiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Utente> utenti = UtenteDAO.getAll();

        // NON toccare attivo qui!
        // Deve mostrare il valore reale del DB

        request.setAttribute("utenti", utenti);
        request.getRequestDispatcher("/pages/admin/admin-utenti.jsp").forward(request, response);
    }
}
