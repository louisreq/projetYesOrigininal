package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.CapteurDaoImpl;
import hei.devweb.traderz.entities.Capteur;

public class CapteurManager {


    private static class CapteurManagerHolder {
        private static CapteurManager instance = new CapteurManager();
    }

    public static CapteurManager getInstance() {
        return CapteurManagerHolder.instance;
    }

    private CapteurDaoImpl capteurDao = new CapteurDaoImpl();

    public Capteur GetActualTempAndHumidity(){return capteurDao.GetActualTempAndHumidity();}

}
