package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Capteur;
import org.json.simple.JSONArray;

public interface CapteurDao {
    Capteur GetActualTempAndHumidity();
    JSONArray GetAllSensorsInfoWithDates(String date_debut, String heure_debut, String date_fin, String heure_fin);
    Capteur GetActualAllSensorsInfo();
}
