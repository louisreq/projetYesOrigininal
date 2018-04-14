package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.AdminDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {


    /**
     * Méthode permettant de récupperer le mot de passe corespondant associer à un pseudo
     * @param username , string contenant le pseudo de l'administrateur
     * @return retourne une string contenant le mot de passe associé à l'admin
     */

    public String getStoredPassword(String username) {
        String password = null;
        String query = "SELECT admin_password FROM administrateurs WHERE admin_nom = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = resultSet.getString("admin_password");
                }
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }


    /**
     * Création d'un objet admin à partir des information d'un administrateur dans la table administrateurs
     * @param name  String contenant le nom de l'admin à créer
     * @return
     */
    public Admin CreateAdminFromName (String name ){

        String query = "SELECT * FROM administrateurs WHERE admin_nom = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){

                    return new Admin (
                            resultSet.getInt("admin_id"),
                            resultSet.getString("admin_nom"),
                            resultSet.getString("admin_password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
