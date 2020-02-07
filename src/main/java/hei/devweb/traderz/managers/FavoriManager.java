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
    public void AddFavori (Integer id_personne, Integer principale_ou_secondaire, Integer id_salle){ favoriDao.AddFavori(id_personne, principale_ou_secondaire, id_salle);}
    public Boolean IsSalleFavori(Integer user_id, Integer salle_id){return favoriDao.IsSalleFavori(user_id, salle_id);}
    public void DeleteFavoriFromSalleIdAndUserId (Integer id_salle, Integer id_user){ favoriDao.DeleteFavoriFromSalleIdAndUserId(id_salle, id_user);}
}
