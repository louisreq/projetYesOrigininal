package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.CampusDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Campus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampusDaoImpl implements CampusDao {

    public List<Campus> GetListOfCampus() {
        List<Campus> liste_campus = new ArrayList<>();
        String query = "SELECT\n" +
                "    campus.id as id_campus,\n" +
                "    campus.nom_campus as nom_campus, \n" +
                "null as liste_salles" +
                "\n" +
                "FROM campus\n" +
                "GROUP BY campus.id";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_campus.add(new Campus(
                            resultSet.getInt("id_campus"),
                            resultSet.getString("nom_campus"),
                            null));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste_campus;
    }

}
