package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.CampusDaoImpl;
import hei.devweb.traderz.entities.Campus;

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

}
