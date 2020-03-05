package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Formulaire_Partie1;
import org.json.simple.JSONArray;

import java.util.List;

public interface Formulaire_Partie1Dao {
    List<Formulaire_Partie1> GetAllFormPartie1();
    JSONArray GetAllQuestionnairesInfoWithDates(String date_debut, String date_fin);
}
