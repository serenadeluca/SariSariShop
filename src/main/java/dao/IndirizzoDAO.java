package dao;

import model.Indirizzo;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndirizzoDAO {

    public List<Indirizzo> getIndirizziByUtente(int idUtente) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM indirizzo_utente WHERE id_utente = ?"
        );
        ps.setInt(1, idUtente);

        ResultSet rs = ps.executeQuery();
        List<Indirizzo> lista = new ArrayList<>();

        while (rs.next()) {
            Indirizzo i = new Indirizzo();
            i.setIdIndirizzo(rs.getInt("id_indirizzo"));
            i.setIdUtente(idUtente);
            i.setIndirizzoRiga1(rs.getString("indirizzo_riga1"));
            i.setIndirizzoRiga2(rs.getString("indirizzo_riga2"));
            i.setCitta(rs.getString("citta"));
            i.setCodicePostale(rs.getString("codice_postale"));
            i.setNazione(rs.getString("nazione"));
            lista.add(i);
        }

        return lista;
    }

    public void aggiungiIndirizzo(Indirizzo i) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO indirizzo_utente (id_utente, indirizzo_riga1, indirizzo_riga2, citta, codice_postale, nazione) "
            + "VALUES (?, ?, ?, ?, ?, ?)"
        );

        ps.setInt(1, i.getIdUtente());
        ps.setString(2, i.getIndirizzoRiga1());
        ps.setString(3, i.getIndirizzoRiga2());
        ps.setString(4, i.getCitta());
        ps.setString(5, i.getCodicePostale());
        ps.setString(6, i.getNazione());

        ps.executeUpdate();
    }

    public Indirizzo getIndirizzoById(int idIndirizzo) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM indirizzo_utente WHERE id_indirizzo = ?"
        );
        ps.setInt(1, idIndirizzo);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Indirizzo i = new Indirizzo();
            i.setIdIndirizzo(rs.getInt("id_indirizzo"));
            i.setIdUtente(rs.getInt("id_utente"));
            i.setIndirizzoRiga1(rs.getString("indirizzo_riga1"));
            i.setIndirizzoRiga2(rs.getString("indirizzo_riga2"));
            i.setCitta(rs.getString("citta"));
            i.setCodicePostale(rs.getString("codice_postale"));
            i.setNazione(rs.getString("nazione"));
            return i;
        }

        return null;
    }

    public void modificaIndirizzo(Indirizzo i) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE indirizzo_utente SET indirizzo_riga1=?, indirizzo_riga2=?, citta=?, codice_postale=?, nazione=? "
            + "WHERE id_indirizzo=?"
        );

        ps.setString(1, i.getIndirizzoRiga1());
        ps.setString(2, i.getIndirizzoRiga2());
        ps.setString(3, i.getCitta());
        ps.setString(4, i.getCodicePostale());
        ps.setString(5, i.getNazione());
        ps.setInt(6, i.getIdIndirizzo());

        ps.executeUpdate();
    }

    public void eliminaIndirizzo(int idIndirizzo) throws Exception {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM sarisarishop.indirizzo_utente WHERE id_indirizzo = ?"
        );
        ps.setInt(1, idIndirizzo);

        int righe = ps.executeUpdate();
        System.out.println("RIGHE ELIMINATE: " + righe);
    }


}
