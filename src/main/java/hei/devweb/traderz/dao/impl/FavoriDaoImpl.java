package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.FavoriDao;
import hei.devweb.traderz.entities.Favori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriDaoImpl implements FavoriDao {


    public List<Favori> GetListOfFavorisFromUserId(Integer user_id){
        List<Favori> liste_favoris = new ArrayList<>();

        String query = "SELECT \n" +
                "\tuhf.id as id_favori,\n" +
                "    uhf.personne_id as id_personne,\n" +
                "    uhf.salle_id as id_salle,\n" +
                "    campus.nom_campus as nom_campus,\n" +
                "    batiment.nom_batiment as nom_batiment,\n" +
                "    etage.nom_etage as nom_etage,\n" +
                "    salle.nom_salle,\n" +
                "    CASE\n" +
                "\t\tWHEN 1 THEN 'Principale'\n" +
                "        ELSE 'Secondaire'\n" +
                "    END as principale_or_secondaire\n" +
                "\n" +
                "FROM user_has_favoris uhf\n" +
                "INNER JOIN personne ON (personne.id = uhf.personne_id)\n" +
                "INNER JOIN salle ON (salle.id = uhf.salle_id)\n" +
                "INNER JOIN etage ON (salle.etage_id = etage.id)\n" +
                "INNER JOIN batiment ON (etage.batiment_id = batiment.id)\n" +
                "INNER JOIN campus ON (batiment.campus_id = campus.id)\n" +
                "\n" +
                "Where uhf.personne_id = " + user_id;


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_favoris.add(new Favori(
                            resultSet.getInt("id_favori"),
                            resultSet.getInt("id_personne"),
                            resultSet.getInt("id_salle"),
                            resultSet.getString("nom_campus"),
                            resultSet.getString("nom_batiment"),
                            resultSet.getString("nom_etage"),
                            resultSet.getString("nom_salle"),
                            resultSet.getString("principale_or_secondaire")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  liste_favoris;
    }
}
