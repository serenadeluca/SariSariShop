package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Utente;
import utils.DBConnection;

public class UtenteDAO {

    // ============================
    // GET ALL
    // ============================
    public static List<Utente> getAll() {
        List<Utente> utenti = new ArrayList<>();

        // Corretto "utenti" in "utente"
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM utente");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Utente u = new Utente();
                u.setIdUtente(rs.getInt("id_utente"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setNumero(rs.getString("numero"));
                u.setDataCreazione(rs.getTimestamp("data_creazione"));
                u.setAttivo(rs.getInt("attivo") == 1);

                utenti.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return utenti;
    }

    // ============================
    // GET BY ID
    // ============================
    public static Utente getById(int id) {
        Utente u = null;

        // Corretto "utenti" in "utente"
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM utente WHERE id_utente = ?")) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Utente();
                    u.setIdUtente(rs.getInt("id_utente"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setNome(rs.getString("nome"));
                    u.setCognome(rs.getString("cognome"));
                    u.setNumero(rs.getString("numero"));
                    u.setDataCreazione(rs.getTimestamp("data_creazione"));
                    u.setAttivo(rs.getInt("attivo") == 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    // ============================
    // DELETE
    // ============================
    public static void delete(int id) {
        // Corretto "utenti" in "utente"
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM utente WHERE id_utente = ?")) {
            
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("DELETE utente → righe eliminate: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================
    // TOGGLE ATTIVO
    // ============================
    public static void toggleAttivo(int id, boolean attivo) {
        // Corretto "utenti" in "utente"
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE utente SET attivo = ? WHERE id_utente = ?")) {
            
            ps.setInt(1, attivo ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================
    // UPDATE
    // ============================
    public static void update(Utente u) {
        // Corretto "utenti" in "utente"
        String sql = "UPDATE utente SET nome=?, cognome=?, email=?, numero=? WHERE id_utente=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getNumero());
            ps.setInt(5, u.getIdUtente());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}