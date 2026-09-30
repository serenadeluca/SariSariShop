package servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Carrello;
import model.CarrelloItem;
import utils.DBConnection;

public class ConfermaOrdineServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_utente") == null) {
            response.getWriter().write("{\"success\": false, \"error\": \"Login richiesto\"}");
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");

        // Carrello corretto (classe Carrello)
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello == null || carrello.getElementi().isEmpty()) {
            response.getWriter().write("{\"success\": false, \"error\": \"Carrello vuoto\"}");
            return;
        }

        // Indirizzo scelto
        Integer idIndirizzo = (Integer) session.getAttribute("indirizzo_scelto");

        if (idIndirizzo == null) {
            response.getWriter().write("{\"success\": false, \"error\": \"Nessun indirizzo selezionato\"}");
            return;
        }

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1) Inserisci ordine
            String ordineSql = "INSERT INTO ordine (id_utente, id_indirizzo, data_ordine, totale_ordine, stato_ordine, metodo_pagamento) "
                             + "VALUES (?, ?, NOW(), ?, 'In elaborazione', 'Carta')";
            PreparedStatement ordinePs = conn.prepareStatement(ordineSql, Statement.RETURN_GENERATED_KEYS);
            ordinePs.setInt(1, idUtente);
            ordinePs.setInt(2, idIndirizzo);
            ordinePs.setDouble(3, carrello.getTotale());
            ordinePs.executeUpdate();

            ResultSet rsOrdine = ordinePs.getGeneratedKeys();
            rsOrdine.next();
            int idOrdine = rsOrdine.getInt(1);

            // 2) Inserisci righe ordine (tabella corretta: dettaglio_ordine)
            String rigaSql = "INSERT INTO dettaglio_ordine (id_ordine, id_prodotto, quantita, prezzo_unitario) "
                           + "VALUES (?, ?, ?, ?)";
            PreparedStatement rigaPs = conn.prepareStatement(rigaSql);

            for (CarrelloItem item : carrello.getElementi()) {
                rigaPs.setInt(1, idOrdine);
                rigaPs.setInt(2, item.getProdotto().getIdProdotto());
                rigaPs.setInt(3, item.getQuantita());
                rigaPs.setDouble(4, item.getProdotto().getPrezzo());
                rigaPs.addBatch();
            }

            rigaPs.executeBatch();

            conn.commit();

            // Svuota carrello
            session.removeAttribute("carrello");

            response.getWriter().write(
                "{ \"success\": true, \"message\": \"Ordine confermato\", \"id_ordine\": " + idOrdine + " }"
            );

        } catch (Exception e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (Exception ignored) {}

            response.getWriter().write(
                "{ \"success\": false, \"error\": \"Errore durante la conferma dell'ordine\" }"
            );
        }
    }
}
