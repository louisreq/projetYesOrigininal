package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.SalleDaoImpl;
import hei.devweb.traderz.entities.Salle;

import java.util.List;

public class SalleManager {

    private static class SalleManagerHolder {
        private static SalleManager instance = new SalleManager();
    }

    public static SalleManager getInstance() {
        return SalleManagerHolder.instance;
    }

    private SalleDaoImpl salleDao = new SalleDaoImpl();

    public List<Salle> GetListOfSalleFromCampusAndUserInput(Integer id_campus, String user_input){return salleDao.GetListOfSalleFromCampusAndUserInput(id_campus, user_input);}

    public List<Salle> GetAllSalles(){return salleDao.GetAllSalles();}

}
