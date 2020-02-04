package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.CampusDaoImpl;
import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;

import java.util.List;

public class CampusManager {

    private static class CampusManagerHolder {
        private static CampusManager instance = new CampusManager();
    }

    public static CampusManager getInstance() {
        return CampusManagerHolder.instance;
    }

    private CampusDaoImpl campusDao = new CampusDaoImpl();

    public List<Campus> GetListOfCampus(){return campusDao.GetListOfCampus();}

    public List<Salle> GetListOfSalleWithCampusId(Integer campus_id){return campusDao.GetListOfSalleWithCampusId(campus_id);}
    public Campus GetCampusFromSalleId(Integer id_salle){return  campusDao.GetCampusFromSalleId(id_salle);}
    public Campus GetListOfSalleWithCampusName(String name_campus){return  campusDao.GetListOfSalleWithCampusName(name_campus);}
}
