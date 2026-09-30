package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarrelloItem {

    private Prodotto prodotto;
    private int quantita;

    public CarrelloItem(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void aggiungiQuantita(int q) {
        this.quantita += q;
    }

    public double getSubTotale() {
        BigDecimal bd = BigDecimal.valueOf(prodotto.getPrezzo() * quantita);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
