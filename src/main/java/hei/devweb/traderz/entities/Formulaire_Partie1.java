package hei.devweb.traderz.entities;

import javax.persistence.criteria.CriteriaBuilder;

public class Formulaire_Partie1 {
//    Partie 1 : Info Perso
    private Integer id_info_part_1;
    private Integer id_user;
    private Integer sexe;
    private Integer age;
    private String situation;
    private String domaine;
    private String diplome;
    private Boolean parent;
    private String commune;
    private String mot1;
    private String mot2;
    private String mot3;

    public Formulaire_Partie1(Integer id_info_part_1, Integer id_user, Integer sexe, Integer age, String situation, String domaine, String diplome, Boolean parent, String commune, String mot1, String mot2, String mot3) {
        this.id_info_part_1 = id_info_part_1;
        this.id_user = id_user;
        this.sexe = sexe;
        this.age = age;
        this.situation = situation;
        this.domaine = domaine;
        this.diplome = diplome;
        this.parent = parent;
        this.commune = commune;
        this.mot1 = mot1;
        this.mot2 = mot2;
        this.mot3 = mot3;
    }

    public Integer getId_info_part_1() {
        return id_info_part_1;
    }

    public void setId_info_part_1(Integer id_info_part_1) {
        this.id_info_part_1 = id_info_part_1;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getSexe() {
        return sexe;
    }

    public void setSexe(Integer sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getMot1() {
        return mot1;
    }

    public void setMot1(String mot1) {
        this.mot1 = mot1;
    }

    public String getMot2() {
        return mot2;
    }

    public void setMot2(String mot2) {
        this.mot2 = mot2;
    }

    public String getMot3() {
        return mot3;
    }

    public void setMot3(String mot3) {
        this.mot3 = mot3;
    }
}
