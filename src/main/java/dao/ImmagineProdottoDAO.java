package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ImmagineProdotto;
import utils.DBConnection;

public class ImmagineProdottoDAO {

    // Recupera la prima immagine di un prodotto
    public static ImmagineProdotto getImmaginePrincipale(int idProdotto) {
        ImmagineProdotto img = null;
        String sql = "SELECT * FROM immagine_prodotto WHERE id_prodotto = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProdotto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    img = new ImmagineProdotto(
                        rs.getInt("id_immagine"),
                        rs.getInt("id_prodotto"),
                        rs.getString("url_immagine")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    // Inserisce una nuova immagine
    public static void save(int idProdotto, String urlImmagine) {
        if (urlImmagine == null || urlImmagine.trim().isEmpty()) return;

        String sql = "INSERT INTO immagine_prodotto (id_prodotto, url_immagine) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProdotto);
            ps.setString(2, urlImmagine.trim());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Aggiorna l'immagine esistente o ne crea una nuova se non c'era
    public static void updateOInserisci(int idProdotto, String nuovoUrl) {
        if (nuovoUrl == null || nuovoUrl.trim().isEmpty()) return;

        String checkSql = "SELECT id_immagine FROM immagine_prodotto WHERE id_prodotto = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(checkSql)) {

            psCheck.setInt(1, idProdotto);
            try (ResultSet rs = psCheck.executeQuery()) {
                if (rs.next()) {
                    String updateSql = "UPDATE immagine_prodotto SET url_immagine = ? WHERE id_prodotto = ?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                        psUpdate.setString(1, nuovoUrl.trim());
                        psUpdate.setInt(2, idProdotto);
                        psUpdate.executeUpdate();
                    }
                } else {
                    save(idProdotto, nuovoUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Elimina le immagini di un prodotto
    public static void deleteByProdotto(int idProdotto) {
        String sql = "DELETE FROM immagine_prodotto WHERE id_prodotto = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProdotto);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}