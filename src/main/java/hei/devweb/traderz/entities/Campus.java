package hei.devweb.traderz.entities;


import java.util.List;

public class Campus {
    private Integer id;
    private String campus_name;
    private List<Salle> list_salles;

    public Campus(Integer id, String campus_name, List<Salle> list_salles) {
        this.id = id;
        this.campus_name = campus_name;
        this.list_salles = list_salles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampus_name() {
        return campus_name;
    }

    public void setCampus_name(String campus_name) {
        this.campus_name = campus_name;
    }

    public List<Salle> getList_salles() {
        return list_salles;
    }

    public void setList_salles(List<Salle> list_salles) {
        this.list_salles = list_salles;
    }
}
