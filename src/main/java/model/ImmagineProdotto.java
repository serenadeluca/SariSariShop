package model;

public class ImmagineProdotto {

    private int idImmagine;
    private int idProdotto;
    private String urlImmagine;

    public ImmagineProdotto() {}

    public ImmagineProdotto(int idImmagine, int idProdotto, String urlImmagine) {
        this.idImmagine = idImmagine;
        this.idProdotto = idProdotto;
        this.urlImmagine = urlImmagine;
    }

    public int getIdImmagine() {
        return idImmagine;
    }

    public void setIdImmagine(int idImmagine) {
        this.idImmagine = idImmagine;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }
}
