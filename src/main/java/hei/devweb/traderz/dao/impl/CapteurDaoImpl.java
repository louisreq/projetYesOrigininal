package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.CapteurDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Capteur;

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
                "FROM yes_3024.sensors as s\n" +
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
}
