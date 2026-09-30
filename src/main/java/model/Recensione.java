package model;

import java.util.Date;

public class Recensione {

    private int idRecensione;
    private int idProdotto;
    private int idUtente;
    private String titolo;
    private String commento;
    private Date dataPubblicazione;
    private boolean acquistoVerificato;
    private int voto;

    // Costruttore vuoto
    public Recensione() {
    }

    // Costruttore completo (opzionale)
    public Recensione(int idRecensione, int idProdotto, int idUtente,
                      String titolo, String commento, Date dataPubblicazione,
                      boolean acquistoVerificato, int voto) {
        this.idRecensione = idRecensione;
        this.idProdotto = idProdotto;
        this.idUtente = idUtente;
        this.titolo = titolo;
        this.commento = commento;
        this.dataPubblicazione = dataPubblicazione;
        this.acquistoVerificato = acquistoVerificato;
        this.voto = voto;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public boolean isAcquistoVerificato() {
        return acquistoVerificato;
    }

    public void setAcquistoVerificato(boolean acquistoVerificato) {
        this.acquistoVerificato = acquistoVerificato;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "Recensione{" +
                "idRecensione=" + idRecensione +
                ", idProdotto=" + idProdotto +
                ", idUtente=" + idUtente +
                ", titolo='" + titolo + '\'' +
                ", commento='" + commento + '\'' +
                ", dataPubblicazione=" + dataPubblicazione +
                ", acquistoVerificato=" + acquistoVerificato +
                ", voto=" + voto +
                '}';
    }
}
