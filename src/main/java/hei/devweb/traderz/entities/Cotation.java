package hei.devweb.traderz.entities;

public class Cotation {
    private Integer idCotation;
    private String cotationNom;
    private String categorie;
    private Double prix;
    private Double haut;
    private Double bas;
    private Double varjour;
    private Double veille;
    private Double ouverture;
    private int volume;

    /**
     * Constructeur de l'objet cotation
     * @param idCotation id de la cotation
     * @param cotationNom nom de la cotation
     * @param categorie categorie de la cotation
     * @param prix prix de la cotation
     * @param haut de la cotation
     * @param bas de la cotation
     * @param varjour de la cotation
     * @param veille de la cotation
     * @param ouverture de la cotation
     * @param volume de la cotation
     */
    public Cotation(Integer idCotation, String cotationNom, String categorie, Double prix, Double haut, Double bas, Double varjour, Double veille, Double ouverture, int volume) {
        this.idCotation = idCotation;
        this.cotationNom = cotationNom;
        this.categorie = categorie;
        this.prix = prix;
        this.haut = haut;
        this.bas = bas;
        this.varjour = varjour;
        this.veille = veille;
        this.ouverture = ouverture;
        this.volume=volume;
    }

    public Integer getIdCotation() {
        return idCotation;
    }

    public void setIdCotation(Integer idCotation) {
        this.idCotation = idCotation;
    }

    public String getCotationNom() {        return cotationNom;    }

    public void setCotationNom(String cotationNom) {        this.cotationNom = cotationNom;    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getHaut() {
        return haut;
    }

    public void setHaut(Double haut) {
        this.haut = haut;
    }

    public Double getBas() {
        return bas;
    }

    public void setBas(Double bas) {
        this.bas = bas;
    }

    public Double getVarjour() {
        return varjour;
    }

    public void setVarjour(Double varjour) {
        this.varjour = varjour;
    }

    public Double getVeille() {
        return veille;
    }

    public void setVeille(Double veille) {
        this.veille = veille;
    }

    public Double getOuverture() {
        return ouverture;
    }

    public void setOuverture(Double ouverture) {
        this.ouverture = ouverture;
    }

    public int getVolume() {        return volume;    }

    public void setVolume(int volume) {        this.volume = volume;    }
}