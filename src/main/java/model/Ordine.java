package model;

import java.sql.Timestamp;

public class Ordine {

    private int idOrdine;
    private int idUtente;
    private int idIndirizzo;
    private Timestamp dataOrdine;
    private double totaleOrdine;
    private String statoOrdine;
    private String metodoPagamento;
	

    public Ordine() {}

    public int getIdOrdine() { return idOrdine; }
    public void setIdOrdine(int idOrdine) { this.idOrdine = idOrdine; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public int getIdIndirizzo() { return idIndirizzo; }
    public void setIdIndirizzo(int idIndirizzo) { this.idIndirizzo = idIndirizzo; }

    public Timestamp getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(Timestamp dataOrdine) { this.dataOrdine = dataOrdine; }

    public double getTotaleOrdine() { return totaleOrdine; }
    public void setTotaleOrdine(double totaleOrdine) { this.totaleOrdine = totaleOrdine; }

    public String getStatoOrdine() { return statoOrdine; }
    public void setStatoOrdine(String statoOrdine) { this.statoOrdine = statoOrdine; }

    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }
    
    public void setData(Timestamp dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public void setTotale(double totaleOrdine) {
        this.totaleOrdine = totaleOrdine;
    }

}
