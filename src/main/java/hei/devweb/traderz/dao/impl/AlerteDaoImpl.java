package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.AlerteDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Alerte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlerteDaoImpl implements AlerteDao {

    public void AddAlerte (Object datetime, String message, Integer personne_id, Integer salle_id, String titre){
        String query = "INSERT INTO `alerte`(`date`, `message`, `personne_id`, `salle_id`, `titre`)\n" +
                "VALUE (?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setObject(1, datetime);
            statement.setString(2, message);
            statement.setInt(3, personne_id);
            statement.setInt(4, salle_id);
            statement.setString(5, titre);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Add this ALERT /!\\/!\\ ");
        }
    }

    public List<Alerte> GetAlertesFromUserId(Integer user_id){
        List<Alerte> liste_alerte = new ArrayList<>();
        String query = "select\n" +
                "\talerte.id as id_alerte,\n" +
                "    alerte.date as date_alerte,\n" +
                "    alerte.message as message,\n" +
                "    alerte.personne_id as id_personne,\n" +
                "    alerte.salle_id as id_salle\n," +
                "    alerte.titre as titre\n" +
                "\n" +
                "from alerte\n" +
                "\n" +
                "where alerte.personne_id = " + user_id;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_alerte.add(new Alerte(
                            resultSet.getInt("id_alerte"),
                            resultSet.getTimestamp("date_alerte"),
                            resultSet.getString("message"),
                            resultSet.getInt("id_personne"),
                            resultSet.getInt("id_salle"),
                            resultSet.getString("titre")
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste_alerte;
    }
}
