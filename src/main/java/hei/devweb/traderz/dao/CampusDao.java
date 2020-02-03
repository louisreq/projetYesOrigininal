package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;

import java.util.List;

public interface CampusDao {

    List<Campus> GetListOfCampus();
    List<Salle> GetListOfSalleWithCampusId(Integer campus_id);
}
