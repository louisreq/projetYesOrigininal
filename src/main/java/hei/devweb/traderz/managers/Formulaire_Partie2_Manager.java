package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.Formulaire_Partie2DaoImpl;
import hei.devweb.traderz.entities.Formulaire_Partie2;

import java.util.List;

public class Formulaire_Partie2_Manager {

    private static class Formulaire_Partie2_ManagerHolder {
        private static Formulaire_Partie2_Manager instance = new Formulaire_Partie2_Manager();
    }

    public static Formulaire_Partie2_Manager getInstance() {
        return Formulaire_Partie2_ManagerHolder.instance;
    }

    private Formulaire_Partie2DaoImpl formulaire_partie2Dao = new Formulaire_Partie2DaoImpl();

    public List<Formulaire_Partie2> GetAllFormPartie2(String date_debut, String date_fin){return formulaire_partie2Dao.GetAllFormPartie2(date_debut, date_fin);}



}
