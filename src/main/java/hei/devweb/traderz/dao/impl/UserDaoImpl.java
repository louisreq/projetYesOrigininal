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



    /**
     * Méthode permettant de récupperer le mot de passe corespondant associer à un pseudo utilisateur
     * @param username String contenant le pseudo
     * @return String contenant le mot de passe associé au pseudo de l'user
     */
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

    /**Methode qui teste si le pseudo choisit par l'utilisateur existe deja
     *
     * @param username ,pseudo entré dans le formulaire
     * @return booleen  vrai si pseudo existe dans la BDD, faux sinon
     */
    public boolean UserDontExist (String username) {
        boolean test = false;
        String query = "SELECT COUNT(*) FROM utilisateurs WHERE user_pseudo =?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                if (resultSet.getDouble(1)!= 0) {  // retourne le resultat de la valeur en double de la requête et le compare avec 0
                    {
                        test = true;
                    }
                }
                statement.close();
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return test;
    }



    /**
     * Methode permettant de créer un nouvel utilisateur
     * @param newUser  objet user qui sera crée dans la base de données
     * @return  retourne l'objet user avec son identifiant si l'objet à bien été ajouté dans les tables , retourne null sinon
     */
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

    /**
     * Methode supprimant les informations d'un utilisateurs dans la table
     * @param pseudo String contenant le pseudo de l'utilisateur à supprimer
     */
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


    /**
     * Methode permettant de modifier le mot de passe , remplace l'ancien par le nouveau dans les tables

     * @param newpassword String contenant le nouveau mot de passe à mettre à la place de l'ancien
     * @param username string contenant l'ancien mot de passe
     */

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



    /**
     * Methode permettant de modifier l'adresse mail
     *
     * @param newMail String contenant le nouveau mail à mettre dans la table à la place de l'ancien
     * @param username String contenant l'ancien mail à remplacer
     */
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




    /**
     * Méthode qui retourne une liste d'objet User (contenant un seul user, le user connecté en session)
     * @param pseudo String contenant le pseudo corespondant à l'objet user à retourner
     * @return un objet user
     */
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


    /**
     * Création d'un objet user à partir des informations d'un utilisateur dans la table utilisateurs
     * @param pseudo String contenant le pseudo de l'objet user à retourner
     * @return un objet user contenant toute les informations de l'utilisateur ayant le pseudo utilisé
     */
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

    /**
     * Mise à jour de la valeurs du portefeuille de l'utilisateur suite à l'achat d'une transaction
     * @param liquidite double contenant les nouvelles liquité de l'utilisateur pseudo
     * @param valeurportef double contenant la nouvelle valeur du portefeuille
     * @param valeurtransac double contenant la valeur de la transaction effectuée
     * @param pseudo string contenant le pseudo de l'objet user sur lequel realiser la methode
     */
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

    /**
     *Mise à jour de la valeurs du portefeuille de l'utilisateur suite à la vente d'une transaction
     * @param liquidite  double contenant les nouvelles liquité de l'utilisateur pseudo
     * @param valeurportef double contenant la nouvelle valeur du portefeuille
     * @param gain  double contenant le gain effectuer en réalisant cette transaction
     * @param valeurachat double contenant la valeur d'achat de la cotation
     * @param pseudo string contenant le pseudo de l'objet user sur lequel realiser la methode
     */
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


    /**
     * Retourne un Double qui correspond au montant de liquidité du portefeuille
     * @param pseudo String contenant le pseudo de l'objet user qui contient les informations sur les liquidités de l'utilisateur
     * @return un double contenant les liquidités de l'utilisateur si le pseudo existe dans la table utilisateurs, null sinon
     */
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


    /**
     * Retourne un Double qui correspond au montant total des cotations présentes dans le portefeuille
     * @param pseudo  String contenant le pseudo de l'objet user qui contient les informations sur la valeur des cotations de l'utilisateur
     * @return  un double contenant la valeur des cotations de l'utilisateur si le pseudo existe dans la table utilisateurs, null sinon
     */
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
