package servlet.controller;

import dao.RecensioneDAO;
import model.Recensione;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// NESSUNA ANNOTAZIONE @WebServlet QUI
public class AdminRecensioniServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        RecensioneDAO dao = new RecensioneDAO();
        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                int idRecensione = Integer.parseInt(request.getParameter("id"));
                dao.deleteRecensione(idRecensione);
            }

            List<Recensione> recensioni = dao.getAllRecensioni();
            request.setAttribute("recensioniList", recensioni);

            // Redireziona alla pagina JSP dentro /pages/admin/
            request.getRequestDispatcher("/pages/admin/admin-recensioni.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella gestione delle recensioni.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}