package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.Formulaire_Partie1Dao;
import hei.devweb.traderz.entities.Formulaire_Partie1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Formulaire_Partie1DaoImpl implements Formulaire_Partie1Dao {

    public List<Formulaire_Partie1> GetAllFormPartie1() {

        List<Formulaire_Partie1> Liste_form_partie_1 = new ArrayList<>();

        String query = "SELECT * FROM enquete GROUP BY id_user";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Liste_form_partie_1.add(new Formulaire_Partie1(
                            resultSet.getInt("id_info_enquete"),
                            resultSet.getInt("id_user"),
                            resultSet.getInt("sexe"),
                            resultSet.getInt("age"),
                            resultSet.getString("situation"),
                            resultSet.getString("domaine"),
                            resultSet.getString("diplome"),
                            resultSet.getBoolean("parent"),
                            resultSet.getString("commune"),
                            resultSet.getString("mot1"),
                            resultSet.getString("mot2"),
                            resultSet.getString("mot3")
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Liste_form_partie_1;
    }
}
