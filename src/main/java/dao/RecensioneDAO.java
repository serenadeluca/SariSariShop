package dao;

import model.Recensione;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {

    public void inserisciRecensione(Recensione r) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "INSERT INTO recensione (id_prodotto, id_utente, titolo, commento, data_pubblicazione, acquisto_verificato, voto) "
                   + "VALUES (?, ?, ?, ?, NOW(), ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, r.getIdProdotto());
        ps.setInt(2, r.getIdUtente());
        ps.setString(3, r.getTitolo());
        ps.setString(4, r.getCommento());
        ps.setBoolean(5, r.isAcquistoVerificato());
        ps.setInt(6, r.getVoto());

        ps.executeUpdate();
        conn.close();
    }

    public List<Recensione> getRecensioniByProdotto(int idProdotto) throws Exception {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM recensione WHERE id_prodotto = ? ORDER BY data_pubblicazione DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idProdotto);

        ResultSet rs = ps.executeQuery();

        List<Recensione> list = new ArrayList<>();

        while (rs.next()) {
            Recensione r = new Recensione();
            r.setIdRecensione(rs.getInt("id_recensione"));
            r.setIdProdotto(rs.getInt("id_prodotto"));
            r.setIdUtente(rs.getInt("id_utente"));
            r.setTitolo(rs.getString("titolo"));
            r.setCommento(rs.getString("commento"));
            r.setDataPubblicazione(rs.getTimestamp("data_pubblicazione"));
            r.setAcquistoVerificato(rs.getBoolean("acquisto_verificato"));
            r.setVoto(rs.getInt("voto"));

            list.add(r);
        }

        conn.close();
        return list;
    }
    
    
  
    public List<Recensione> getAllRecensioni() throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM recensione ORDER BY data_pubblicazione DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Recensione> list = new ArrayList<>();
        while (rs.next()) {
            Recensione r = new Recensione();
            r.setIdRecensione(rs.getInt("id_recensione"));
            r.setIdProdotto(rs.getInt("id_prodotto"));
            r.setIdUtente(rs.getInt("id_utente"));
            r.setTitolo(rs.getString("titolo"));
            r.setCommento(rs.getString("commento"));
            r.setDataPubblicazione(rs.getTimestamp("data_pubblicazione"));
            r.setAcquistoVerificato(rs.getBoolean("acquisto_verificato"));
            r.setVoto(rs.getInt("voto"));
            list.add(r);
        }
        conn.close();
        return list;
    }

    public void deleteRecensione(int idRecensione) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM recensione WHERE id_recensione = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idRecensione);
        ps.executeUpdate();
        conn.close();
    }
}
