package hei.devweb.traderz.entities;

import java.time.LocalDate;

// Création d'un objet User
public class User {
    private Integer idUser;
    private String prenom;
    private String nom;
    private String mail;
    private LocalDate dateNaissance;
    private String sexe;
    private String identifiant;
    private String mdp;
    private double liquidites;
    private double valeur;

//  Constructeur de l'objet User

    /**
     * Constructeur de l'ojet User
     * @param idUser id du user
     * @param prenom du user
     * @param nom du user
     * @param identifiant du user
     * @param mdp du user
     * @param mail du user
     * @param dateNaissance du user
     * @param sexe du user
     * @param liquidites du user
     * @param valeur du user
     */
    public User(Integer idUser, String prenom, String nom, String identifiant, String mdp,String mail, LocalDate dateNaissance, String sexe,double liquidites, double valeur){
        this.idUser=idUser;
        this.prenom=prenom;
        this.nom=nom;
        this.mail=mail;
        this.dateNaissance =dateNaissance;
        this.sexe=sexe;
        this.identifiant=identifiant;
        this.mdp=mdp;
        this.liquidites=liquidites;
        this.valeur=valeur;
    }

//Getters et Setters des différents parmètres  de l'objet User

    public String getSexe() {        return sexe;    }
    public void setSexe(String sexe) {        this.sexe = sexe;    }
    public double getLiquidites() {        return liquidites;    }
    public void setLiquidites(double liquidites) {     this.liquidites = liquidites;    }
    public double getValeur() {        return valeur;    }
    public void setValeur(double valeur) {        this.valeur = valeur;    }
    public Integer getIdUser() {        return idUser;    }
    public void setIdUser(Integer idUser) {        this.idUser = idUser;    }
    public String getPrenom() {  return prenom; }
    public void setPrenom(String prenom) {    this.prenom = prenom;   }
    public String getNom() {       return nom;    }
    public void setNom(String nom) {        this.nom = nom;    }
    public String getMail() {        return mail;    }
    public void setMail(String mail) {        this.mail = mail;    }
    public LocalDate getDateNaissance() {        return dateNaissance;    }
    public void setDateNaissance(LocalDate dateNaissance) {        this.dateNaissance = dateNaissance;    }
    public String getIdentifiant() {        return identifiant;    }
    public void setIdentifiant(String identifiant) {        this.identifiant = identifiant;    }
    public String getMdp() {        return mdp;    }
    public void setMdp(String mdp) {        this.mdp = mdp;    }
}

