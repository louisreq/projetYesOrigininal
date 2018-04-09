package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.UserDao;
import hei.devweb.traderz.entities.Transaction;
import hei.devweb.traderz.entities.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe contenant l'implémentation de l'ensemble des méthodes concernant l'utilisateur

public class UserDaoImpl implements UserDao{

// Méthode permettant de récupperer le mot de passe corespondant associer à un pseudo
    public String getStoredPassword(String username) {
        String password = null;
        String query = "SELECT user_password FROM utilisateurs WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = resultSet.getString("user_password");
                }
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

// Methode permettant de créer un nouvel utilisateur

    public User addUser(User newUser){
        String query ="INSERT INTO utilisateurs(user_prenom,user_nom,user_pseudo,user_password,user_mail,user_date_birth,user_sex,user_liquidites,user_valeur)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,newUser.getPrenom());
            statement.setString(2,newUser.getNom());
            statement.setString(3,newUser.getIdentifiant());
            statement.setString(4,newUser.getMdp());
            statement.setString(5,newUser.getMail());
            statement.setDate(6,java.sql.Date.valueOf(newUser.getDateNaissance()));
            statement.setString(7,newUser.getSexe());
            statement.setDouble(8,newUser.getLiquidites());
            statement.setDouble(9,newUser.getValeur());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    newUser.setIdUser(generatedId);
                    return newUser;
                }
            }
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
        return null;
    }

    public void supprimerUser(String pseudo){
        String query = "DELETE FROM utilisateurs WHERE user_pseudo=?";/*UPDATE article SET supprimer=true WHERE idArticle=?*/
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,pseudo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Methode permettant de modifier le mot de passe

    public void modifyPassword (String newpassword, String username){
        String query ="UPDATE utilisateurs SET user_password = ? WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ){
            statement.setString(1, newpassword);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

// Methode permettant de modifier l'adresse mail

    public void modifyMail (String newMail, String username){
        String query ="UPDATE utilisateurs SET user_mail = ? WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ){
            statement.setString(1, newMail);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


// Méthode qui retourne une liste d'objet User qui contient un seul User (l'utilisateur connecté)
// Nous permet d'acceder à toutes ses informations

    public List<User> listuserconnecte(String pseudo){
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs WHERE user_pseudo= ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
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

    public User CreateUserFromPseudo (String pseudo ){

        String query = "SELECT * FROM utilisateurs WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){

                    return new User (
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_prenom"),
                            resultSet.getString("user_nom"),
                            resultSet.getString("user_pseudo"),
                            resultSet.getString("user_password"),
                            resultSet.getString("user_mail"),
                            resultSet.getDate("user_date_birth").toLocalDate(),
                            resultSet.getString("user_sex"),
                            resultSet.getDouble("user_liquidites"),
                            resultSet.getDouble("user_valeur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void Debiter (Double liquidite, Double valeurportef, Double valeurtransac, String pseudo){

        String query = "UPDATE utilisateurs SET user_liquidites = ?, user_valeur = ? WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ){
            statement.setDouble(1, liquidite-valeurtransac);
            statement.setDouble(2, valeurportef+valeurtransac);
            statement.setString(3, pseudo);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void Crediter (Double liquidite,Double valeurportef, Double gain, Double valeurachat, String pseudo){

        String query = "UPDATE utilisateurs SET user_liquidites = ?, user_valeur=? WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ){
            statement.setDouble(1, liquidite+gain+valeurachat);
            statement.setDouble(2,valeurportef-gain-valeurachat );
            statement.setString(3, pseudo);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    //Retourne un objet Double qui correspond au montant de liquidité du portefeuille
    public Double CreateLiquidite (String pseudo){
        String query = "SELECT user_liquidites FROM utilisateurs WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Double(resultSet.getDouble("user_liquidites"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Retourne un objet Double qui correspond au montant de valeur en cotation du portefeuille
    public Double CreateValeur (String pseudo){
        String query = "SELECT user_valeur FROM utilisateurs WHERE user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Double(resultSet.getDouble("user_valeur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





}
