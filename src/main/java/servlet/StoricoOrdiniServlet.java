package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Ordine;

public class StoricoOrdiniServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/SariSariPool");
        } catch (NamingException e) {
            throw new ServletException("Errore nel recupero della Connection Pool", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int idUtente = (int) session.getAttribute("id_utente");
        ArrayList<Ordine> ordini = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {

            String sql = "SELECT id_ordine, data_ordine, totale_ordine, stato_ordine, metodo_pagamento "
                       + "FROM ordine WHERE id_utente = ? ORDER BY data_ordine DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUtente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ordine o = new Ordine();
                o.setIdOrdine(rs.getInt("id_ordine"));
                o.setDataOrdine(rs.getTimestamp("data_ordine"));
                o.setTotaleOrdine(rs.getDouble("totale_ordine"));
                o.setStatoOrdine(rs.getString("stato_ordine"));
                o.setMetodoPagamento(rs.getString("metodo_pagamento"));
                ordini.add(o);
            }

            request.setAttribute("ordini", ordini);
            request.getRequestDispatcher("/pages/storicoOrdini.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home?error=storico_fail");
        }
    }
}
