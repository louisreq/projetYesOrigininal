package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Favori;

import java.util.List;

public interface FavoriDao {

    public List<Favori> GetListOfFavorisFromUserId(Integer user_id);
}
