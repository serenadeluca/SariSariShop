package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.DettaglioOrdine;
import model.Ordine;
import utils.DBConnection;

public class FatturaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.sendError(400, "ID ordine mancante");
            return;
        }

        int idOrdine = Integer.parseInt(idParam);

        try {
            Connection conn = DBConnection.getConnection();

            // ============================
            // 1) RECUPERA ORDINE
            // ============================
            String sqlOrdine = "SELECT * FROM ordine WHERE id_ordine = ?";
            PreparedStatement psOrdine = conn.prepareStatement(sqlOrdine);
            psOrdine.setInt(1, idOrdine);

            ResultSet rsOrdine = psOrdine.executeQuery();

            if (!rsOrdine.next()) {
                response.sendError(404, "Ordine non trovato");
                return;
            }

            Ordine ordine = new Ordine();
            ordine.setIdOrdine(rsOrdine.getInt("id_ordine"));
            ordine.setIdUtente(rsOrdine.getInt("id_utente"));
            ordine.setIdIndirizzo(rsOrdine.getInt("id_indirizzo"));

            ordine.setDataOrdine(rsOrdine.getTimestamp("data_ordine"));   // QUI
            ordine.setTotaleOrdine(rsOrdine.getDouble("totale_ordine"));   // QUI

            ordine.setStatoOrdine(rsOrdine.getString("stato_ordine"));
            ordine.setMetodoPagamento(rsOrdine.getString("metodo_pagamento"));

            request.setAttribute("ordine", ordine);

            // ============================
            // 2) RECUPERA DETTAGLI ORDINE
            // ============================
            String sqlDettagli =
                    "SELECT d.*, p.nome " +
                    "FROM dettaglio_ordine d " +
                    "JOIN prodotto p ON d.id_prodotto = p.id_prodotto " +
                    "WHERE d.id_ordine = ?";

            PreparedStatement psDettagli = conn.prepareStatement(sqlDettagli);
            psDettagli.setInt(1, idOrdine);

            ResultSet rsDettagli = psDettagli.executeQuery();

            List<DettaglioOrdine> dettagli = new ArrayList<>();

            while (rsDettagli.next()) {

                // Usa il tuo costruttore
                DettaglioOrdine d = new DettaglioOrdine();

                d.setIdProdotto(rsDettagli.getInt("id_prodotto"));
                d.setNomeProdotto(rsDettagli.getString("nome"));
                d.setQuantita(rsDettagli.getInt("quantita"));
                d.setPrezzoUnitario(rsDettagli.getDouble("prezzo_unitario"));

                dettagli.add(d);
            }

            request.setAttribute("righe", dettagli);
            request.getSession().setAttribute("ordineFattura", ordine);
            request.getSession().setAttribute("righeFattura", dettagli);

            // ============================
            // 3) MOSTRA JSP FATTURA
            // ============================
            request.getRequestDispatcher("/pages/fattura.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore generazione fattura");
        }
    }
}
