package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.SalleDao;
import hei.devweb.traderz.entities.Salle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalleDaoImpl implements SalleDao {

    public List<String> SplitUserInput(String user_input){
        List<String> splitted_input;
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

    public Salle GetSalleFromId(Integer id_salle) {


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
                "WHERE salle.id = " + id_salle + " \n" +
                "ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    return new Salle(
                            resultSet.getInt("id_salle"),
                            resultSet.getString("nom_salle"),
                            resultSet.getInt("id_etage"),
                            resultSet.getString("nom_etage"),
                            resultSet.getInt("id_batiment"),
                            resultSet.getString("nom_batiment"),
                            resultSet.getInt("id_campus"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray GetTemperature(){

        //Creating a JSONObject object
//        JSONObject jsonObject = new JSONObject();

//        String[] nullable = new String[0];
        JSONArray array = new JSONArray();
        String query = "SELECT\n" +
                "\ts.time_info_collected as time_info_collected,\n" +
                "    s.temperature,\n" +
                "    s.humid\n" +
                "FROM yes_3024.sensors as s";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    JSONObject record = new JSONObject();
                    record.put("time_info_collected", resultSet.getString("time_info_collected"));
                    record.put("temperature", resultSet.getString("temperature"));
                    record.put("humid", resultSet.getString("humid"));
                    array.add(record);
            } }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  array;
    }




    public List<Salle> GetListOfFavoriteSallesFromUserIdAndCampusId(Integer user_id, Integer campus_id){
        List<Salle> liste_favorite_salle = new ArrayList<>();

        String query = "SELECT\n" +
                "\n" +
                "\ts1.id as id_salle,\n" +
                "    s1.nom_salle as nom_salle,\n" +
                "    e1.id as id_etage,\n" +
                "    e1.nom_etage as nom_etage,\n" +
                "    b1.id as id_batiment,\n" +
                "    b1.nom_batiment as nom_batiment,\n" +
                "    c1.id as id_campus,\n" +
                "    c1.nom_campus as nom_campus\n" +
                "FROM salle as s1 \n" +
                "INNER JOIN etage AS e1 ON (s1.etage_id = e1.id)\n" +
                "INNER JOIN batiment AS b1 ON (e1.batiment_id = b1.id)\n" +
                "INNER JOIN campus as c1 ON (b1.campus_id = c1.id) \n" +
                "\n" +
                "WHERE c1.id = " + campus_id + "\n" +
                "AND NOT EXISTS(\n" +
                "\tSELECT\n" +
                "\n" +
                "\t\ts2.id as id_salle\n" +
                "\t\n" +
                "\tFROM user_has_favoris as uhf\n" +
                "\tINNER JOIN personne AS p2 ON (uhf.personne_id = p2.id)\n" +
                "\tINNER JOIN salle AS s2 ON (uhf.salle_id = s2.id)\n" +
                "\tINNER JOIN etage AS e2 ON (s2.etage_id = e2.id)\n" +
                "\tINNER JOIN batiment AS b2 ON (e2.batiment_id = b2.id)\n" +
                "\tINNER JOIN campus AS c2 ON (b2.campus_id = c2.id) \n" +
                "\tWHERE p2.id = " + user_id +"\n" +
                "\tAND c2.id = " + campus_id + "\n" +
                "    AND s1.id = s2.id\n" +
                ")";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_favorite_salle.add(new Salle(
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
        return  liste_favorite_salle;
    }



}
