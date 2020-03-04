package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.SalleDaoImpl;
import hei.devweb.traderz.entities.Salle;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

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
    public Salle GetSalleFromId(Integer id_salle){return salleDao.GetSalleFromId(id_salle);}
    public List<Salle> GetListOfFavoriteSallesFromUserIdAndCampusId(Integer user_id, Integer campus_id){return salleDao.GetListOfFavoriteSallesFromUserIdAndCampusId(user_id, campus_id);}

    public JSONArray GetTemperature(String date_debut, String heure_debut, String date_fin, String heure_fin){return salleDao.GetTemperature(date_debut, heure_debut, date_fin, heure_fin);}
    public Map<Integer, String> GetAllSallesMapedWithIdAndCampusName(){return salleDao.GetAllSallesMapedWithIdAndCampusName();}
    public Map<Integer, String> GetAllSallesMapedWithIdAndEtage(){return salleDao.GetAllSallesMapedWithIdAndEtage();}
//    public JSONArray GetActualTempAndHumidity(){return  salleDao.GetActualTempAndHumidity();}

}
