package dao;

import model.DettaglioOrdine;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DettaglioOrdineDAO {

    private DataSource dataSource;

    public DettaglioOrdineDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<DettaglioOrdine> getDettagli(int idOrdine) throws Exception {

        String sql = "SELECT d.id_prodotto, p.nome, d.quantita, d.prezzo_unitario "
                   + "FROM dettaglio_ordine d "
                   + "JOIN prodotto p ON d.id_prodotto = p.id_prodotto "
                   + "WHERE d.id_ordine = ?";

        List<DettaglioOrdine> list = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrdine);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DettaglioOrdine det = new DettaglioOrdine(dataSource);
                    det.setIdProdotto(rs.getInt("id_prodotto"));
                    det.setNomeProdotto(rs.getString("nome"));
                    det.setQuantita(rs.getInt("quantita"));
                    det.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
                    list.add(det);
                }
            }
        }

        return list;
    }
}
