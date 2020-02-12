package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Capteur;

public interface CapteurDao {
    Capteur GetActualTempAndHumidity();
}
