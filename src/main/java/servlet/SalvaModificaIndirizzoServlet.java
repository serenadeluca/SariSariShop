package servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utils.DBConnection;

public class SalvaModificaIndirizzoServlet extends HttpServlet {
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

        // Parametri
        String idIndirizzoStr = request.getParameter("id_indirizzo");
        String riga1 = request.getParameter("riga1");
        String riga2 = request.getParameter("riga2");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String nazione = request.getParameter("nazione");

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

            // 2) Aggiornamento indirizzo
            String sql = "UPDATE indirizzo_utente SET indirizzo_riga1 = ?, indirizzo_riga2 = ?, "
                       + "citta = ?, codice_postale = ?, nazione = ? WHERE id_indirizzo = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, riga1);
            ps.setString(2, riga2);
            ps.setString(3, citta);
            ps.setString(4, cap);
            ps.setString(5, nazione);
            ps.setInt(6, idIndirizzo);

            ps.executeUpdate();

            // Risposta JSON di successo
            response.getWriter().write(
                "{ \"success\": true, \"message\": \"Modifica salvata\" }"
            );

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(
                "{ \"success\": false, \"error\": \"Errore durante il salvataggio\" }"
            );
        }
    }
}
