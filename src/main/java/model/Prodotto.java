package model;

public class Prodotto {

    private int idProdotto;             // id_prodotto
    private Integer idCategoria;        // id_categoria
    private String nome;                // nome
    private String urlNomeProdotto;     // url_nome_prodotto
    private String descrizione;         // descrizione
    private double prezzo;              // prezzo
    private String quantitaDaTenere;    // quantita_da_tenere (varchar(50))
    private boolean pubblico;           // pubblico
    private String dataCreazione;       // data_creazione
    private String dataAggiornamento;   // data_aggiornamento
    private String immagine;            // immagine

    public Prodotto() {}

    // Costruttore principale con String
    public Prodotto(int idProdotto, Integer idCategoria, String nome, String urlNomeProdotto,
                    String descrizione, double prezzo, String quantitaDaTenere,
                    boolean pubblico, String dataCreazione, String dataAggiornamento,
                    String immagine) {

        this.idProdotto = idProdotto;
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.urlNomeProdotto = urlNomeProdotto;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantitaDaTenere = quantitaDaTenere;
        this.pubblico = pubblico;
        this.dataCreazione = dataCreazione;
        this.dataAggiornamento = dataAggiornamento;
        this.immagine = immagine;
    }

    // OVERLOAD Costruttore: accetta anche int per quantitaDaTenere senza dare errore
    public Prodotto(int idProdotto, Integer idCategoria, String nome, String urlNomeProdotto,
                    String descrizione, double prezzo, int quantitaDaTenere,
                    boolean pubblico, String dataCreazione, String dataAggiornamento,
                    String immagine) {

        this(idProdotto, idCategoria, nome, urlNomeProdotto, descrizione, prezzo, 
             String.valueOf(quantitaDaTenere), pubblico, dataCreazione, dataAggiornamento, immagine);
    }

    // GETTER
    public int getIdProdotto() { return idProdotto; }
    public Integer getIdCategoria() { return idCategoria; }
    public String getNome() { return nome; }
    public String getUrlNomeProdotto() { return urlNomeProdotto; }
    public String getDescrizione() { return descrizione; }
    public double getPrezzo() { return prezzo; }
    public String getQuantitaDaTenere() { return quantitaDaTenere; }
    public boolean isPubblico() { return pubblico; }
    public String getDataCreazione() { return dataCreazione; }
    public String getDataAggiornamento() { return dataAggiornamento; }
    public String getImmagine() { return immagine; }

    // SETTER STANDARD (Accetta String)
    public void setQuantitaDaTenere(String quantitaDaTenere) { 
        this.quantitaDaTenere = quantitaDaTenere; 
    }

    // ✅ FIX ERRORE: OVERLOAD SETTER (Accetta int e lo converte in String in automatico)
    public void setQuantitaDaTenere(int quantitaDaTenere) { 
        this.quantitaDaTenere = String.valueOf(quantitaDaTenere); 
    }

    // ALTRI SETTER
    public void setIdProdotto(int idProdotto) { this.idProdotto = idProdotto; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }
    public void setNome(String nome) { this.nome = nome; }
    public void setUrlNomeProdotto(String urlNomeProdotto) { this.urlNomeProdotto = urlNomeProdotto; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }
    public void setPubblico(boolean pubblico) { this.pubblico = pubblico; }
    public void setDataCreazione(String dataCreazione) { this.dataCreazione = dataCreazione; }
    public void setDataAggiornamento(String dataAggiornamento) { this.dataAggiornamento = dataAggiornamento; }
    public void setImmagine(String immagine) { this.immagine = immagine; }


}