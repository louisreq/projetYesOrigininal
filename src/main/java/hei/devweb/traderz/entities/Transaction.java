package hei.devweb.traderz.entities;

public class Transaction {
    private Integer idTransac;
    private String transacUserPseudo;
    private String transacCotationCategorie;
    private String transacCotationNom;
    private Integer transacCotationId;
    private  Double transacPrix;
    private Double transacVolume;
    private Boolean transacSens;
    private Double gain;


    public Transaction(Integer idTransac, String transacUserPseudo, String transacCotationCategorie, String transacCotationNom, Integer transacCotationId, Double transacPrix, Double transacVolume, Boolean transacSens) {
        this.idTransac = idTransac;
        this.transacUserPseudo = transacUserPseudo;
        this.transacCotationCategorie = transacCotationCategorie;
        this.transacCotationNom = transacCotationNom;
        this.transacCotationId = transacCotationId;
        this.transacPrix = transacPrix;
        this.transacVolume = transacVolume;
        this.transacSens = transacSens;
    }

    public Transaction(Integer idTransac, String transacUserPseudo, String transacCotationCategorie, String transacCotationNom, Integer transacCotationId, Double transacPrix, Double transacVolume, Boolean transacSens, Double gain) {
        this.idTransac = idTransac;
        this.transacUserPseudo = transacUserPseudo;
        this.transacCotationCategorie = transacCotationCategorie;
        this.transacCotationNom = transacCotationNom;
        this.transacCotationId = transacCotationId;
        this.transacPrix = transacPrix;
        this.transacVolume = transacVolume;
        this.transacSens = transacSens;
        this.gain = gain;

    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Integer getIdTransac() {
        return idTransac;
    }

    public void setIdTransac(Integer idTransac) {
        this.idTransac = idTransac;
    }

    public String getTransacUserPseudo() {
        return transacUserPseudo;
    }

    public void setTransacUserPseudo(String transacUserPseudo) {
        this.transacUserPseudo = transacUserPseudo;
    }

    public String getTransacCotationCategorie() {
        return transacCotationCategorie;
    }

    public void setTransacCotationCategorie(String transacCotationCategorie) {
        this.transacCotationCategorie = transacCotationCategorie;
    }

    public String getTransacCotationNom() {
        return transacCotationNom;
    }

    public void setTransacCotationNom(String transacCotationNom) {
        this.transacCotationNom = transacCotationNom;
    }

    public Integer getTransacCotationId() {
        return transacCotationId;
    }

    public void setTransacCotationId(Integer transacCotationId) {
        this.transacCotationId = transacCotationId;
    }

    public Double getTransacPrix() {
        return transacPrix;
    }

    public void setTransacPrix(Double transacPrix) {
        this.transacPrix = transacPrix;
    }

    public Double getTransacVolume() {
        return transacVolume;
    }

    public void setTransacVolume(Double transacVolume) {
        this.transacVolume = transacVolume;
    }

    public Boolean getTransacSens() {
        return transacSens;
    }

    public void setTransacSens(Boolean transacSens) {
        this.transacSens = transacSens;
    }
}

