package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.FavoriDaoImpl;
import hei.devweb.traderz.entities.Favori;

import java.util.List;

public class FavoriManager {

    private static class FavoriManagerHolder {
        private static FavoriManager instance = new FavoriManager();
    }

    public static FavoriManager getInstance() {
        return FavoriManagerHolder.instance;
    }

    private FavoriDaoImpl favoriDao = new FavoriDaoImpl();


    public List<Favori> GetListOfFavorisFromUserId(Integer user_id){return favoriDao.GetListOfFavorisFromUserId(user_id);}
}
