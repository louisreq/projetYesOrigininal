package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.CapteurDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Capteur;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CapteurDaoImpl implements CapteurDao {

    public Capteur GetActualTempAndHumidity(){

        Capteur capteur_actuel = null;

        String query = "SELECT\n" +
                "\ts.time_info_collected as time_info_collected,\n" +
                "    s.temperature,\n" +
                "    s.humid\n" +
                "FROM sensors as s\n" +
                "ORDER BY s.time_info_collected DESC\n" +
                "LIMIT 1\n";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    capteur_actuel = new Capteur(
                            null,
                            null,
                            resultSet.getTimestamp("time_info_collected"),
                            null, null, null, null, null,
                            resultSet.getFloat("humid"),
                            null, null,
                            resultSet.getFloat("temperature"),
                            null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  capteur_actuel;
    }

    public JSONArray GetAllSensorsInfoWithDates(String date_debut, String heure_debut, String date_fin, String heure_fin){


        JSONArray array = new JSONArray();
        String query = "SELECT * \n" +
                "FROM sensors s\n" +
                "\n" +
                "WHERE s.time_info_collected >= '" + date_debut + " " + heure_debut + "'" +
                "\nAND s.time_info_collected <= '" + date_fin + " " + heure_fin + "'";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    JSONObject record = new JSONObject();
                    record.put("id_sensor", resultSet.getString("id_sensor"));
                    record.put("id_rasberry", resultSet.getString("id_rasberry"));
                    record.put("time_info_collected", resultSet.getString("time_info_collected"));
                    record.put("CO", resultSet.getString("CO"));
                    record.put("CO2_", resultSet.getString("CO2"));
                    record.put("LMG", resultSet.getString("LMG"));
                    record.put("dust", resultSet.getString("dust"));
                    record.put("formol", resultSet.getString("formol"));
                    record.put("temperature", resultSet.getString("temperature"));
                    record.put("humid", resultSet.getString("humid"));
                    record.put("light", resultSet.getString("light"));
                    record.put("sound", resultSet.getString("sound"));
                    record.put("tolu", resultSet.getString("tolu"));

                    array.add(record);
                } }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(array);
        return  array;
    }

    public Capteur GetActualAllSensorsInfo(){

        Capteur capteur_actuel = null;

        String query = "SELECT * \n" +
                "FROM sensors as s\n" +
                "ORDER BY s.time_info_collected DESC\n" +
                "LIMIT 1\n";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    capteur_actuel = new Capteur(
                            resultSet.getInt("id_sensor"),
                            resultSet.getInt("id_rasberry"),
                            resultSet.getTimestamp("time_info_collected"),
                            resultSet.getFloat("CO"),
                            resultSet.getFloat("CO2"),
                            resultSet.getFloat("LMG"),
                            resultSet.getFloat("dust"),
                            resultSet.getFloat("formol"),
                            resultSet.getFloat("humid"),
                            resultSet.getInt("light"),
                            resultSet.getInt("sound"),
                            resultSet.getFloat("temperature"),
                            resultSet.getFloat("tolu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  capteur_actuel;
    }

}
