package dao;

import model.Amministratore;
import javax.sql.DataSource;
import java.sql.*;

public class AmministratoreDAO {

    private DataSource dataSource;

    public AmministratoreDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Amministratore login(String email, String password) throws SQLException {

        String sql = "SELECT * FROM amministratore WHERE email = ? AND password_hash = SHA2(?, 256)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Amministratore admin = new Amministratore();
                    admin.setId(rs.getInt("id_amministratore"));
                    admin.setUsername(rs.getString("username"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPasswordHash(rs.getString("password_hash"));
                    admin.setLivelloAccesso(rs.getInt("livello_accesso"));
                    return admin;
                }
            }
        }

        return null;
    }

    /**
     * Inserisce un nuovo amministratore nella tabella 'amministratore'
     */
    public boolean registra(String username, String email, String password, int livelloAccesso) throws SQLException {
        
        // Rispetta esattamente le colonne dello screenshot
        String sql = "INSERT INTO amministratore (username, email, password_hash, livello_accesso, notifica_nuovi_ordini) "
                   + "VALUES (?, ?, SHA2(?, 256), ?, 1)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, livelloAccesso);

            return ps.executeUpdate() > 0;
        }
    }
}