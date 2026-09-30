package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Carrello {

    private List<CarrelloItem> elementi = new ArrayList<>();

    public List<CarrelloItem> getElementi() {
        return elementi;
    }

    public void aggiungiProdotto(Prodotto p, int quantita) {

        for (CarrelloItem item : elementi) {
            if (item.getProdotto().getIdProdotto() == p.getIdProdotto()) {
                item.aggiungiQuantita(quantita);
                return;
            }
        }

        elementi.add(new CarrelloItem(p, quantita));
    }

    public void rimuoviProdotto(int idProdotto) {
        elementi.removeIf(item -> item.getProdotto().getIdProdotto() == idProdotto);
    }

    public double getTotale() {
        BigDecimal totale = BigDecimal.ZERO;

        for (CarrelloItem item : elementi) {
            totale = totale.add(BigDecimal.valueOf(item.getSubTotale()));
        }

        return totale.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
