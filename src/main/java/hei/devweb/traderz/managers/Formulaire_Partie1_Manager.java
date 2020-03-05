package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.Formulaire_Partie1DaoImpl;
import hei.devweb.traderz.entities.Formulaire_Partie1;
import org.json.simple.JSONArray;

import java.util.List;

public class Formulaire_Partie1_Manager {

    private static class Formulaire_Partie1_ManagerHolder {
        private static Formulaire_Partie1_Manager instance = new Formulaire_Partie1_Manager();
    }

    public static Formulaire_Partie1_Manager getInstance() {
        return Formulaire_Partie1_ManagerHolder.instance;
    }

    private Formulaire_Partie1DaoImpl formulaire_partie1Dao = new Formulaire_Partie1DaoImpl();

    public List<Formulaire_Partie1> GetAllFormPartie1(){return formulaire_partie1Dao.GetAllFormPartie1();}
    public JSONArray GetAllQuestionnairesInfoWithDates(String date_debut, String date_fin){return formulaire_partie1Dao.GetAllQuestionnairesInfoWithDates(date_debut, date_fin);}

}
