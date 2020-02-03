package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.AlerteDaoImpl;
import hei.devweb.traderz.entities.Alerte;

import java.util.List;

public class AlerteManager {

    private static class AlerteManagerHolder {
        private static AlerteManager instance = new AlerteManager();
    }

    public static AlerteManager getInstance() {
        return AlerteManagerHolder.instance;
    }

    private AlerteDaoImpl alerteDao = new AlerteDaoImpl();


    public void AddAlerte (Object datetime, String message, Integer personne_id, Integer salle_id, String titre){ alerteDao.AddAlerte(datetime, message, personne_id, salle_id, titre); }
    public List<Alerte> GetAlertesFromUserId(Integer user_id){return alerteDao.GetAlertesFromUserId(user_id);}
}
