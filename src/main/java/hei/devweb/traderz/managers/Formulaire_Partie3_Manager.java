package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.Formulaire_Partie3DaoImpl;
import hei.devweb.traderz.entities.Formulaire_Partie3;

import java.util.List;

public class Formulaire_Partie3_Manager {

    private static class Formulaire_Partie3_ManagerHolder {
        private static Formulaire_Partie3_Manager instance = new Formulaire_Partie3_Manager();
    }

    public static Formulaire_Partie3_Manager getInstance() {
        return Formulaire_Partie3_ManagerHolder.instance;
    }

    private Formulaire_Partie3DaoImpl formulaire_partie3Dao = new Formulaire_Partie3DaoImpl();

    public List<Formulaire_Partie3> GetAllFormPartie3(){return formulaire_partie3Dao.GetAllFormPartie3();}

}
