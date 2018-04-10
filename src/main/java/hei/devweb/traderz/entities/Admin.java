package hei.devweb.traderz.entities;

public class Admin {
    private Integer id;
    private String nom;
    private String password;

    public Admin(Integer id, String nom, String password) {
        this.id = id;
        this.nom = nom;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
