package hei.devweb.traderz.entities;

public class Favori {
    private Integer id_favori;
    private Integer id_user;
    private Integer id_salle;
    private String nom_salle;
    private String principale_or_secondaire;

    public Favori(Integer id_favori, Integer id_user, Integer id_salle, String nom_salle, String principale_or_secondaire) {
        this.id_favori = id_favori;
        this.id_user = id_user;
        this.id_salle = id_salle;
        this.nom_salle = nom_salle;
        this.principale_or_secondaire = principale_or_secondaire;
    }

    public Integer getId_favori() {
        return id_favori;
    }

    public void setId_favori(Integer id_favori) {
        this.id_favori = id_favori;
    }


    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_salle() {
        return id_salle;
    }

    public void setId_salle(Integer id_salle) {
        this.id_salle = id_salle;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public void setNom_salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public String getPrincipale_or_secondaire() {
        return principale_or_secondaire;
    }

    public void setPrincipale_or_secondaire(String principale_or_secondaire) {
        this.principale_or_secondaire = principale_or_secondaire;
    }
}
