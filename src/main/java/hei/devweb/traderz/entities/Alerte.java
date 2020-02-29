package hei.devweb.traderz.entities;

import java.sql.Timestamp;

public class Alerte {
    private Integer id;
    private Timestamp datetime;
    private String message;
    private Integer personne_id;
    private Integer salle_id;
    private String titre;
    private Boolean checked;
    private String nom_personne;


//    CONSTRUCTEUR NORMAL D'UNE ALERTE
    public Alerte(Integer id, Timestamp datetime, String message, Integer personne_id, Integer salle_id, String titre, Boolean checked) {
        this.id = id;
        this.datetime = datetime;
        this.message = message;
        this.personne_id = personne_id;
        this.salle_id = salle_id;
        this.titre = titre;
        this.checked = checked;
    }

//    CONSTRUCTEUR AVEC LE NOM ET PRENOM De la PERSONNE (AU LIEUX DE L'ID)
    public Alerte(Integer id, Timestamp datetime, String message, Integer salle_id, String titre, Boolean checked, String nom_personne) {
        this.id = id;
        this.datetime = datetime;
        this.message = message;
        this.salle_id = salle_id;
        this.titre = titre;
        this.checked = checked;
        this.nom_personne = nom_personne;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPersonne_id() {
        return personne_id;
    }

    public void setPersonne_id(Integer personne_id) {
        this.personne_id = personne_id;
    }

    public Integer getSalle_id() {
        return salle_id;
    }

    public void setSalle_id(Integer salle_id) {
        this.salle_id = salle_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getNom_personne() {
        return nom_personne;
    }

    public void setNom_personne(String nom_personne) {
        this.nom_personne = nom_personne;
    }
}
