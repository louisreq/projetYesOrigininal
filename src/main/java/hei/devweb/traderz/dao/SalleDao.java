package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Salle;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

public interface SalleDao {
    List<Salle> GetListOfSalleFromCampusAndUserInput(Integer id_campus, String user_input);
    List<Salle> GetAllSalles();
    Salle GetSalleFromId(Integer id_salle);
    List<Salle> GetListOfFavoriteSallesFromUserIdAndCampusId(Integer user_id, Integer campus_id);
    JSONArray GetTemperature();
    public Map<Integer, String> GetAllSallesMapedWithIdAndCampusName();
    public Map<Integer, String> GetAllSallesMapedWithIdAndEtage();
//    JSONArray GetActualTempAndHumidity();
}
