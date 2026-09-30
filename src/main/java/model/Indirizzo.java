package model;

public class Indirizzo {

    private int idIndirizzo;
    private int idUtente;
    private String indirizzoRiga1;
    private String indirizzoRiga2;
    private String citta;
    private String codicePostale;
    private String nazione;

    public Indirizzo() {}

    public int getIdIndirizzo() {
        return idIndirizzo;
    }

    public void setIdIndirizzo(int idIndirizzo) {
        this.idIndirizzo = idIndirizzo;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getIndirizzoRiga1() {
        return indirizzoRiga1;
    }

    public void setIndirizzoRiga1(String indirizzoRiga1) {
        this.indirizzoRiga1 = indirizzoRiga1;
    }

    public String getIndirizzoRiga2() {
        return indirizzoRiga2;
    }

    public void setIndirizzoRiga2(String indirizzoRiga2) {
        this.indirizzoRiga2 = indirizzoRiga2;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCodicePostale() {
        return codicePostale;
    }

    public void setCodicePostale(String codicePostale) {
        this.codicePostale = codicePostale;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
}
