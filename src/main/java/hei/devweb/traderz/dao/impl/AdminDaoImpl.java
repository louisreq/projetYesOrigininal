package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.AdminDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Admin;
import hei.devweb.traderz.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * Création d'un objet admin à partir des informations d'un administrateur dans la table administrateurs
     * @param name  String contenant le nom de l'admin à créer
     * @return un objet Java Admin
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

    public List<User> listOfUser (){
        List<User> users= new ArrayList<>();
        String query = "SELECT * FROM utilisateurs ORDER BY user_pseudo ";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_prenom"),
                            resultSet.getString("user_nom"),
                            resultSet.getString("user_pseudo"),
                            resultSet.getString("user_password"),
                            resultSet.getString("user_mail"),
                            resultSet.getDate("user_date_birth").toLocalDate(),
                            resultSet.getString("user_sex"),
                            resultSet.getDouble("user_liquidites"),
                            resultSet.getDouble("user_valeur")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void DeleteUser (Integer userid){
        String query = "DELETE FROM utilisateurs WHERE user_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,userid );
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
    }

    public List<User> ListOfUserByName (String lettres){
        List<User> users= new ArrayList<>();
        String query = "SELECT * FROM utilisateurs WHERE user_pseudo LIKE ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                statement.setString(1,lettres+'%');
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_prenom"),
                            resultSet.getString("user_nom"),
                            resultSet.getString("user_pseudo"),
                            resultSet.getString("user_password"),
                            resultSet.getString("user_mail"),
                            resultSet.getDate("user_date_birth").toLocalDate(),
                            resultSet.getString("user_sex"),
                            resultSet.getDouble("user_liquidites"),
                            resultSet.getDouble("user_valeur")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
