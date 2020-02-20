package hei.devweb.traderz.entities;

import java.sql.Timestamp;

public class Rasberry {
    private Integer id_capteur_salle;
    private String nom_rasberry;
    private Integer id_salle;
    private String emplacement;
    private Timestamp date_debut;
    private Timestamp date_fin;

    public Rasberry(Integer id_capteur_salle, String nom_rasberry, Integer id_salle, String emplacement, Timestamp date_debut, Timestamp date_fin) {
        this.id_capteur_salle = id_capteur_salle;
        this.nom_rasberry = nom_rasberry;
        this.id_salle = id_salle;
        this.emplacement = emplacement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Integer getId_capteur_salle() {
        return id_capteur_salle;
    }

    public void setId_capteur_salle(Integer id_capteur_salle) {
        this.id_capteur_salle = id_capteur_salle;
    }

    public String getNom_rasberry() {
        return nom_rasberry;
    }

    public void setNom_rasberry(String nom_rasberry) {
        this.nom_rasberry = nom_rasberry;
    }

    public Integer getId_salle() {
        return id_salle;
    }

    public void setId_salle(Integer id_salle) {
        this.id_salle = id_salle;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }
}
