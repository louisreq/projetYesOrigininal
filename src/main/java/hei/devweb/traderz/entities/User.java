package hei.devweb.traderz.entities;

import java.time.LocalDate;

// Création d'un objet User
public class User {
    private Integer idUser;
    private String nom;
    private String prenom;
    private String mail;
    private String sexe;
    private String mdp;
    private String role;
//  Constructeur de l'objet User

    /**
     * Constructeur de l'ojet User
     * @param idUser id du user
     * @param prenom du user
     * @param nom du user
     * @param mdp  password du user
     * @param mail du user
     * @param role (User or Admin)
     */


    public User(Integer idUser,  String nom, String prenom, String mail, String sexe, String mdp, String role) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.sexe = sexe;
        this.mdp = mdp;
        this.role = role;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}











