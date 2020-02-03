package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Salle;

import java.util.List;

public interface SalleDao {
    List<Salle> GetListOfSalleFromCampusAndUserInput(Integer id_campus, String user_input);
    List<Salle> GetAllSalles();

}
