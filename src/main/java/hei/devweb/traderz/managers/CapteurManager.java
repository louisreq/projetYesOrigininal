package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.CapteurDaoImpl;
import hei.devweb.traderz.entities.Capteur;
import org.json.simple.JSONArray;

public class CapteurManager {


    private static class CapteurManagerHolder {
        private static CapteurManager instance = new CapteurManager();
    }

    public static CapteurManager getInstance() {
        return CapteurManagerHolder.instance;
    }

    private CapteurDaoImpl capteurDao = new CapteurDaoImpl();

    public Capteur GetActualTempAndHumidity(){return capteurDao.GetActualTempAndHumidity();}

    public JSONArray GetAllSensorsInfoWithDates(String date_debut, String heure_debut, String date_fin, String heure_fin){return capteurDao.GetAllSensorsInfoWithDates(date_debut, heure_debut, date_fin, heure_fin);}

    public Capteur GetActualAllSensorsInfo(){return capteurDao.GetActualAllSensorsInfo();}


}
