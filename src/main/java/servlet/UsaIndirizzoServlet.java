package servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utils.DBConnection;

public class UsaIndirizzoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Risposta JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        // Utente non loggato → JSON
        if (session == null || session.getAttribute("id_utente") == null) {
            response.getWriter().write(
                "{ \"success\": false, \"error\": \"Login richiesto\" }"
            );
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");

        // Parametro ID indirizzo
        String idIndirizzoStr = request.getParameter("id_indirizzo");

        if (idIndirizzoStr == null) {
            response.getWriter().write(
                "{ \"success\": false, \"error\": \"ID indirizzo mancante\" }"
            );
            return;
        }

        int idIndirizzo = Integer.parseInt(idIndirizzoStr);

        try {
            Connection conn = DBConnection.getConnection();

            // 1) Verifica che l’indirizzo appartenga all’utente
            String checkSql = "SELECT id_indirizzo FROM indirizzo_utente WHERE id_indirizzo = ? AND id_utente = ?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, idIndirizzo);
            checkPs.setInt(2, idUtente);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                response.getWriter().write(
                    "{ \"success\": false, \"error\": \"Indirizzo non trovato o non autorizzato\" }"
                );
                return;
            }

            // 2) Salva l’indirizzo scelto nella sessione
            session.setAttribute("indirizzo_scelto", idIndirizzo);

            // Risposta JSON di successo
            response.getWriter().write(
                "{ \"success\": true, \"message\": \"Indirizzo selezionato\" }"
            );

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(
                "{ \"success\": false, \"error\": \"Errore durante la selezione\" }"
            );
        }
    }
}
