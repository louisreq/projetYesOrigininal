package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.FavoriDao;
import hei.devweb.traderz.entities.Favori;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriDaoImpl implements FavoriDao {

    public void AddFavori (Integer id_personne, Integer principale_ou_secondaire, Integer id_salle){
        String query = "INSERT INTO `user_has_favoris`(`personne_id`, `is_favori_preffered`, `salle_id`)\n" +
                "VALUE (?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, id_personne);
            statement.setInt(2, principale_ou_secondaire);
            statement.setInt(3, id_salle);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Add this to Favorite /!\\/!\\ ");
        }
    }

    public void DeleteFavoriFromSalleIdAndUserId (Integer id_salle, Integer id_user){
        String query = "DELETE FROM `user_has_favoris` WHERE personne_id = " + id_user + " AND salle_id = " + id_salle;

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Delete this to Favorite /!\\/!\\ ");
        }
    }

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
                "\t\tWHEN uhf.is_favori_preffered=1 THEN 'Principale'\n" +
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
            System.out.println(statement);
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

    public Boolean IsSalleFavori(Integer user_id, Integer salle_id){
//        List<Favori> liste_favoris = new ArrayList<>();

        String query = "SELECT \n" +
                "\tCASE \n" +
                "\t\tWHEN EXISTS (\n" +
                "\t\t\tSELECT \n" +
                "\t\t\t\t*\n" +
                "\t\t\tFROM user_has_favoris uhf\n" +
                "\n" +
                "\t\t\twhere uhf.personne_id= " + user_id + " \n" +
                "\t\t\tand uhf.salle_id = " + salle_id + " \n" +
                "\t\t\t) THEN TRUE\n" +
                "\t\tELSE FALSE\n" +
                "\tEND as favori";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Boolean res = resultSet.getBoolean("favori");
                   return res;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
