package model;

import javax.sql.DataSource;

public class DettaglioOrdine {

    private int idProdotto;
    private String nomeProdotto;
    private int quantita;
    private double prezzoUnitario;

    public DettaglioOrdine(DataSource dataSource) {
		// TODO Auto-generated constructor stub
	}
    public DettaglioOrdine() {}

	public int getIdProdotto() { return idProdotto; }
    public void setIdProdotto(int idProdotto) { this.idProdotto = idProdotto; }

    public String getNomeProdotto() { return nomeProdotto; }
    public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = nomeProdotto; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public double getPrezzoUnitario() { return prezzoUnitario; }
    public void setPrezzoUnitario(double prezzoUnitario) { this.prezzoUnitario = prezzoUnitario; }
	public Object getDettagli(int idOrdine) {
		// TODO Auto-generated method stub
		return null;
	}
}
