package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.AdminDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {

    // Méthode permettant de récupperer le mot de passe corespondant associer à un pseudo
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

    public Admin CreateAdminFromName (String name ){

        String query = "SELECT * FROM utilisateurs WHERE user_pseudo = ?";
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
