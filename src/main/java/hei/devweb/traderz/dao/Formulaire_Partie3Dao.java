package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Formulaire_Partie3;

import java.util.List;

public interface Formulaire_Partie3Dao {

    List<Formulaire_Partie3> GetAllFormPartie3(String date_debut, String date_fin) ;

}
