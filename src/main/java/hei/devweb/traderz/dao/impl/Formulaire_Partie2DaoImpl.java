package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.Formulaire_Partie2Dao;
import hei.devweb.traderz.entities.Formulaire_Partie2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Formulaire_Partie2DaoImpl implements Formulaire_Partie2Dao {

    public List<Formulaire_Partie2> GetAllFormPartie2() {

        List<Formulaire_Partie2> Liste_form_partie_2 = new ArrayList<>();

        String query = "SELECT * FROM sensation_salles";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Liste_form_partie_2.add(new Formulaire_Partie2(
                            resultSet.getInt("id_sensation"),
                            resultSet.getInt("id_salle"),
                            resultSet.getInt("id_salle"),
                            resultSet.getInt("id_sensor"),
                            resultSet.getString("q_air_hei"),
                            resultSet.getString("dist_fenetre"),
                            resultSet.getString("dist_ventilo"),
                            resultSet.getString("climat_salle"),
                            resultSet.getString("temp_sensation"),
                            resultSet.getDouble("capteur_temp"),
                            resultSet.getString("air_sensation"),
                            resultSet.getString("air_agreable"),
                            resultSet.getString("odeur"),
                            resultSet.getBoolean("poussiere"),
                            resultSet.getString("symptomes"),
                            resultSet.getString("q_air_salle")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Liste_form_partie_2;
    }

}
