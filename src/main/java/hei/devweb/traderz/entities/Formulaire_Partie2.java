package hei.devweb.traderz.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Formulaire_Partie2 {

    private Integer id_sensation;
    private Integer id_salle;
    private Integer id_user;
    private Integer id_sensor;
    private String qualite_air_hei;
    private String distance_fenetre;
    private String distance_ventilo;
    private String climat_salle;
    private String temp_sensation;
    private Double capteur_temp;
    private String air_sensation;
    private String air_agreable;
    private String odeur;
    private Boolean poussiere;
    private String symptome;
    private String qualite_air_salle;


    public Formulaire_Partie2(Integer id_sensation, Integer id_salle, Integer id_user, Integer id_sensor, String qualite_air_hei, String distance_fenetre, String distance_ventilo, String climat_salle, String temp_sensation, Double capteur_temp, String air_sensation, String air_agreable, String odeur, Boolean poussiere, String symptome, String qualite_air_salle) {
        this.id_sensation = id_sensation;
        this.id_salle = id_salle;
        this.id_user = id_user;
        this.id_sensor = id_sensor;
        this.qualite_air_hei = qualite_air_hei;
        this.distance_fenetre = distance_fenetre;
        this.distance_ventilo = distance_ventilo;
        this.climat_salle = climat_salle;
        this.temp_sensation = temp_sensation;
        this.capteur_temp = capteur_temp;
        this.air_sensation = air_sensation;
        this.air_agreable = air_agreable;
        this.odeur = odeur;
        this.poussiere = poussiere;
        this.symptome = symptome;
        this.qualite_air_salle = qualite_air_salle;
    }


    public Integer getId_sensation() {
        return id_sensation;
    }

    public void setId_sensation(Integer id_sensation) {
        this.id_sensation = id_sensation;
    }

    public Integer getId_salle() {
        return id_salle;
    }

    public void setId_salle(Integer id_salle) {
        this.id_salle = id_salle;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(Integer id_sensor) {
        this.id_sensor = id_sensor;
    }

    public String getQualite_air_hei() {
        return qualite_air_hei;
    }

    public void setQualite_air_hei(String qualite_air_hei) {
        this.qualite_air_hei = qualite_air_hei;
    }

    public String getDistance_fenetre() {
        return distance_fenetre;
    }

    public void setDistance_fenetre(String distance_fenetre) {
        this.distance_fenetre = distance_fenetre;
    }

    public String getDistance_ventilo() {
        return distance_ventilo;
    }

    public void setDistance_ventilo(String distance_ventilo) {
        this.distance_ventilo = distance_ventilo;
    }

    public String getClimat_salle() {
        return climat_salle;
    }

    public void setClimat_salle(String climat_salle) {
        this.climat_salle = climat_salle;
    }

    public String getTemp_sensation() {
        return temp_sensation;
    }

    public void setTemp_sensation(String temp_sensation) {
        this.temp_sensation = temp_sensation;
    }

    public Double getCapteur_temp() {
        return capteur_temp;
    }

    public void setCapteur_temp(Double capteur_temp) {
        this.capteur_temp = capteur_temp;
    }

    public String getAir_sensation() {
        return air_sensation;
    }

    public void setAir_sensation(String air_sensation) {
        this.air_sensation = air_sensation;
    }

    public String getAir_agreable() {
        return air_agreable;
    }

    public void setAir_agreable(String air_agreable) {
        this.air_agreable = air_agreable;
    }

    public String getOdeur() {
        return odeur;
    }

    public void setOdeur(String odeur) {
        this.odeur = odeur;
    }

    public Boolean getPoussiere() {
        return poussiere;
    }

    public void setPoussiere(Boolean poussiere) {
        this.poussiere = poussiere;
    }

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public String getQualite_air_salle() {
        return qualite_air_salle;
    }

    public void setQualite_air_salle(String qualite_air_salle) {
        this.qualite_air_salle = qualite_air_salle;
    }
}
