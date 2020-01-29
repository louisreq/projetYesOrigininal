package hei.devweb.traderz.entities;

public class Salle {
    private Integer id;
    private String salle_name;
    private Integer etage_id;
    private String etage_name;
    private Integer batiment_id;
    private String batiment_name;
    private Integer campus_id;

    public Salle(Integer id, String salle_name, Integer etage_id, String etage_name, Integer batiment_id, String batiment_name, Integer campus_id) {
        this.id = id;
        this.salle_name = salle_name;
        this.etage_id = etage_id;
        this.etage_name = etage_name;
        this.batiment_id = batiment_id;
        this.batiment_name = batiment_name;
        this.campus_id = campus_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalle_name() {
        return salle_name;
    }

    public void setSalle_name(String salle_name) {
        this.salle_name = salle_name;
    }

    public Integer getEtage_id() {
        return etage_id;
    }

    public void setEtage_id(Integer etage_id) {
        this.etage_id = etage_id;
    }

    public String getEtage_name() {
        return etage_name;
    }

    public void setEtage_name(String etage_name) {
        this.etage_name = etage_name;
    }

    public Integer getBatiment_id() {
        return batiment_id;
    }

    public void setBatiment_id(Integer batiment_id) {
        this.batiment_id = batiment_id;
    }

    public String getBatiment_name() {
        return batiment_name;
    }

    public void setBatiment_name(String batiment_name) {
        this.batiment_name = batiment_name;
    }

    public Integer getCampus_id() {
        return campus_id;
    }

    public void setCampus_id(Integer campus_id) {
        this.campus_id = campus_id;
    }
}
