package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Prodotto;
import utils.DBConnection;

public class ProdottoDAO {

    // ========================= MAPPA RESULTSET → MODEL =========================
    private static Prodotto mapResultSetToProdotto(ResultSet rs) throws SQLException {
        Prodotto p = new Prodotto();

        p.setIdProdotto(rs.getInt("id_prodotto"));

        int idCat = rs.getInt("id_categoria");
        p.setIdCategoria(rs.wasNull() ? null : idCat);

        p.setNome(rs.getString("nome"));
        p.setUrlNomeProdotto(rs.getString("url_nome_prodotto"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setPrezzo(rs.getDouble("prezzo"));
        p.setQuantitaDaTenere(rs.getString("quantita_da_tenere"));
        p.setPubblico(rs.getBoolean("pubblico"));
        p.setDataCreazione(rs.getString("data_creazione"));
        p.setDataAggiornamento(rs.getString("data_aggiornamento"));

        // ⭐ FIX: usa la colonna GIUSTA del tuo DB
        p.setImmagine(rs.getString("immagine"));

        return p;
    }

    // ========================= GET ALL =========================
    public static List<Prodotto> getAll() {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotto";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                prodotti.add(mapResultSetToProdotto(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    // ========================= GET BY ID =========================
    public static Prodotto getById(int id) {
        Prodotto p = null;
        String sql = "SELECT * FROM prodotto WHERE id_prodotto = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = mapResultSetToProdotto(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    // ========================= SAVE =========================
    public static void save(Prodotto p) {
        String sql = "INSERT INTO prodotto (id_categoria, nome, url_nome_prodotto, descrizione, prezzo, quantita_da_tenere, pubblico, immagine) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (p.getIdCategoria() != null) {
                ps.setInt(1, p.getIdCategoria());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setString(2, p.getNome());
            ps.setString(3, p.getUrlNomeProdotto());
            ps.setString(4, p.getDescrizione());
            ps.setDouble(5, p.getPrezzo());
            ps.setString(6, p.getQuantitaDaTenere());
            ps.setBoolean(7, p.isPubblico());
            ps.setString(8, p.getImmagine());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    p.setIdProdotto(generatedKeys.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========================= UPDATE =========================
    public static void update(Prodotto p) {
        String sql = "UPDATE prodotto SET id_categoria = ?, nome = ?, url_nome_prodotto = ?, descrizione = ?, prezzo = ?, " +
                     "quantita_da_tenere = ?, pubblico = ?, immagine = ? WHERE id_prodotto = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (p.getIdCategoria() != null) {
                ps.setInt(1, p.getIdCategoria());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setString(2, p.getNome());
            ps.setString(3, p.getUrlNomeProdotto());
            ps.setString(4, p.getDescrizione());
            ps.setDouble(5, p.getPrezzo());
            ps.setString(6, p.getQuantitaDaTenere());
            ps.setBoolean(7, p.isPubblico());
            ps.setString(8, p.getImmagine());
            ps.setInt(9, p.getIdProdotto());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========================= DELETE =========================
    public static void delete(int id) {
        String sql = "DELETE FROM prodotto WHERE id_prodotto = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ========================= ULTIMI ARRIVI =========================
    public static List<Prodotto> getUltimiArrivi(int limit) {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotto ORDER BY id_prodotto DESC LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodotti.add(mapResultSetToProdotto(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    // ========================= PIÙ VENDUTI =========================
    public static List<Prodotto> getPiuVenduti(int limit) {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT p.*, SUM(d.quantita) AS venduti " +
                     "FROM prodotto p " +
                     "JOIN dettaglio_ordine d ON p.id_prodotto = d.id_prodotto " +
                     "GROUP BY p.id_prodotto " +
                     "ORDER BY venduti DESC LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodotti.add(mapResultSetToProdotto(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    // ========================= PUBBLICI =========================
    public static List<Prodotto> getPubblici() {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotto WHERE pubblico = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                prodotti.add(mapResultSetToProdotto(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    // ========================= PRODOTTI PER CATEGORIA =========================
    public static List<Prodotto> getProdottiByCategoria(String categoria) {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT p.* FROM prodotto p " +
                     "JOIN categorie c ON p.id_categoria = c.id_categoria " +
                     "WHERE c.url_nome_prodotto = ? AND p.pubblico = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodotti.add(mapResultSetToProdotto(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    // ========================= NUOVI ARRIVI PER CATEGORIA =========================
    public static List<Prodotto> getNuoviArriviByCategoria(String categoria) {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT p.* FROM prodotto p " +
                     "JOIN categorie c ON p.id_categoria = c.id_categoria " +
                     "WHERE c.url_nome_prodotto = ? AND p.pubblico = 1 " +
                     "ORDER BY p.id_prodotto DESC LIMIT 6";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodotti.add(mapResultSetToProdotto(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    // ========================= CERCA PRODOTTI =========================
    public static List<Prodotto> cercaProdotti(String query) {
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotto WHERE pubblico = 1 AND (nome LIKE ? OR descrizione LIKE ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodotti.add(mapResultSetToProdotto(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodotti;
    }
}
