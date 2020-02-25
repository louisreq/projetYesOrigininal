package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.RasberryDaoImpl;
import hei.devweb.traderz.entities.Rasberry;

import java.util.List;
import java.util.Map;

public class RasberryManager {

    private static class RasberryManagerHolder {
        private static RasberryManager instance = new RasberryManager();
    }

    public static RasberryManager getInstance() {
        return RasberryManagerHolder.instance;
    }

    private RasberryDaoImpl rasberryDao = new RasberryDaoImpl();

    public List<Rasberry> GetAllRasberrySalle(){return rasberryDao.GetAllRasberrySalle();}

    public void AddRasberry (String nom_rasberry){rasberryDao.AddRasberry(nom_rasberry);}
    public void AddRasberrySalle (Rasberry rasberry){rasberryDao.AddRasberrySalle(rasberry);}
    public Map<Integer, String> GetAllRasberry(){return rasberryDao.GetAllRasberry();}
    public void SetDateFinRasberrySalle (Rasberry rasberry){rasberryDao.SetDateFinRasberrySalle(rasberry);}
    public void DeleteRasberry (Rasberry rasberry){rasberryDao.DeleteRasberry(rasberry);}
    public Boolean IsCapteurNameAlreadyTaken(String rasberry_name){return rasberryDao.IsCapteurNameAlreadyTaken(rasberry_name);}
    public void EditRasberry (String old_name, String new_name){rasberryDao.EditRasberry(old_name, new_name);}
    public void EditRasberrySalleName (String old_name, String new_name){rasberryDao.EditRasberrySalleName(old_name, new_name);}

}
