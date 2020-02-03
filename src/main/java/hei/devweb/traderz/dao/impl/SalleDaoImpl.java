package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.SalleDao;
import hei.devweb.traderz.entities.Salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalleDaoImpl implements SalleDao {

    public List<String> SplitUserInput(String user_input){
        List<String> splitted_input = new ArrayList<>();
        splitted_input = Arrays.asList(user_input.split(" "));
        return splitted_input;
    }

    public String GetQueryLikeStatementFromUserInput(List<String> splitted_input){
        String queryLikeStatement = "";
        for(String word_input : splitted_input){
            word_input = word_input.replace(" ", "");
            queryLikeStatement += "  AND ( \n batiment.nom_batiment LIKE " + "'%" + word_input + "%'\n";
            queryLikeStatement += " OR etage.nom_etage LIKE " + "'%" + word_input + "%'\n";
            queryLikeStatement += " OR salle.nom_salle LIKE " + "'%" + word_input + "%'\n)";
        }

        return queryLikeStatement;
    }

    public List<Salle> GetListOfSalleFromCampusAndUserInput(Integer id_campus, String user_input) {
//        On s'occupe de traiter la saisie de l'utilisateur d'abord
        String QueryLikeStatement = GetQueryLikeStatementFromUserInput(SplitUserInput(user_input));


        List<Salle> liste_salles = new ArrayList<>();
//        String user_input_sql = "'%" + user_input + "%'";
//        System.out.println(user_input_sql);
        String query = "SELECT\n" +
                "salle.id as id_salle,\n" +
                "salle.nom_salle as nom_salle,\n" +
                "etage.id as id_etage,\n" +
                "etage.nom_etage as nom_etage,\n" +
                "batiment.id as id_batiment,\n" +
                "batiment.nom_batiment as nom_batiment,\n" +
                "campus.id as id_campus\n" +
                "\n" +
                "\n" +
                "FROM campus\n" +
                "INNER JOIN batiment ON (campus.id = batiment.campus_id)\n" +
                "INNER JOIN etage ON (batiment.id = etage.batiment_id)\n" +
                "INNER JOIN salle ON (etage.id = salle.etage_id)\n" +
                "WHERE campus.id = " + id_campus + "\n" +
                QueryLikeStatement;
        System.out.println(query);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_salles.add(new Salle(
                            resultSet.getInt("id_salle"),
                            resultSet.getString("nom_salle"),
                            resultSet.getInt("id_etage"),
                            resultSet.getString("nom_etage"),
                            resultSet.getInt("id_batiment"),
                            resultSet.getString("nom_batiment"),
                            resultSet.getInt("id_campus")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste_salles;
    }


    public List<Salle> GetAllSalles() {

        List<Salle> liste_salles = new ArrayList<>();

        String query = "SELECT\n" +
                "    salle.id as id_salle,\n" +
                "    salle.nom_salle as nom_salle,\n" +
                "    etage.id as id_etage,\n" +
                "    etage.nom_etage as nom_etage,\n" +
                "    batiment.id as id_batiment,\n" +
                "    batiment.nom_batiment as nom_batiment,\n" +
                "    campus.id as id_campus,\n" +
                "    campus.nom_campus as nom_campus\n" +
                "\n" +
                "\n" +
                "FROM campus\n" +
                "INNER JOIN batiment ON (campus.id = batiment.campus_id)\n" +
                "INNER JOIN etage ON (batiment.id = etage.batiment_id)\n" +
                "INNER JOIN salle ON (etage.id = salle.etage_id)\n" +
                "\n" +
                "ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_salles.add(new Salle(
                            resultSet.getInt("id_salle"),
                            resultSet.getString("nom_salle"),
                            resultSet.getInt("id_etage"),
                            resultSet.getString("nom_etage"),
                            resultSet.getInt("id_batiment"),
                            resultSet.getString("nom_batiment"),
                            resultSet.getInt("id_campus")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste_salles;
    }


//    public ArrayList GetFavoris(Integer userId) {
//
//        List<Integer, Integer, Integer, String> liste_salles = new ArrayList<>();
//
//        String query = "SELECT \n" +
//                "\tuhf.id as id_favori,\n" +
//                "    uhf.personne_id as id_personne,\n" +
//                "    uhf.salle_id as id_salle,\n" +
//                "    CASE\n" +
//                "\t\tWHEN 1 THEN 'Principale'\n" +
//                "        ELSE 'Secondaire'\n" +
//                "    END as principale_or_secondaire\n" +
//                "\n" +
//                "FROM user_has_favoris uhf\n" +
//                "INNER JOIN personne ON (personne.id = uhf.personne_id)\n" +
//                "INNER JOIN salle ON (salle.id = uhf.personne_id)\n" +
//                "\n" +
//                "Where uhf.personne_id = " + userId;
//
//        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
//             PreparedStatement statement = (connection).prepareStatement(query)) {
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//
//                    liste_salles.add(
//                            resultSet.getInt("id_favoris"),
//                            resultSet.getInt("id_personne"),
//                            resultSet.getInt("id_salle"),
//                            resultSet.getString("principale_or_secondaire")
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return liste_salles;
//    }

}
