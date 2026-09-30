package dao;

import model.Ordine;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {

    private DataSource dataSource;

    public OrdineDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // CREA ORDINE
    public int creaOrdine(Ordine ordine) throws Exception {
        String sql = "INSERT INTO ordine (id_utente, id_indirizzo, totale_ordine, stato_ordine, metodo_pagamento) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, ordine.getIdUtente());
            ps.setInt(2, ordine.getIdIndirizzo());
            ps.setDouble(3, ordine.getTotaleOrdine());
            ps.setString(4, ordine.getStatoOrdine());
            ps.setString(5, ordine.getMetodoPagamento());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // id_ordine generato
                }
            }
        }

        return -1;
    }

    public List<Ordine> getOrdiniUtente(int idUtente) throws Exception {
        List<Ordine> ordini = new ArrayList<>();

        String sql = "SELECT id_ordine, id_utente, id_indirizzo, data_ordine, totale_ordine, stato_ordine, metodo_pagamento "
                   + "FROM ordine WHERE id_utente = ? ORDER BY data_ordine DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUtente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ordine o = new Ordine();
                    o.setIdOrdine(rs.getInt("id_ordine"));
                    o.setIdUtente(rs.getInt("id_utente"));
                    o.setIdIndirizzo(rs.getInt("id_indirizzo"));
                    o.setDataOrdine(rs.getTimestamp("data_ordine"));
                    o.setTotaleOrdine(rs.getDouble("totale_ordine"));
                    o.setStatoOrdine(rs.getString("stato_ordine"));
                    o.setMetodoPagamento(rs.getString("metodo_pagamento"));

                    ordini.add(o);
                }
            }
        }

        return ordini;
    }

    public List<Ordine> getAllOrdini() throws Exception {
        List<Ordine> ordini = new ArrayList<>();

        String sql = "SELECT id_ordine, id_utente, id_indirizzo, data_ordine, totale_ordine, stato_ordine, metodo_pagamento "
                   + "FROM ordine ORDER BY data_ordine DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ordine o = new Ordine();
                o.setIdOrdine(rs.getInt("id_ordine"));
                o.setIdUtente(rs.getInt("id_utente"));
                o.setIdIndirizzo(rs.getInt("id_indirizzo"));
                o.setDataOrdine(rs.getTimestamp("data_ordine"));
                o.setTotaleOrdine(rs.getDouble("totale_ordine"));
                o.setStatoOrdine(rs.getString("stato_ordine"));
                o.setMetodoPagamento(rs.getString("metodo_pagamento"));

                ordini.add(o);
            }
        }

        return ordini;
    }

}
