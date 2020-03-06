package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Formulaire_Partie2;

import java.util.List;

public interface Formulaire_Partie2Dao {

    List<Formulaire_Partie2> GetAllFormPartie2(String date_debut, String date_fin);

}
