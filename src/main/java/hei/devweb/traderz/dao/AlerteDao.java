package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Alerte;

import java.util.List;

public interface AlerteDao {
    public List<Alerte> GetAlertesFromUserId(Integer user_id);
    public List<Alerte> GetAllAlertes();
}
