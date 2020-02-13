package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.UserDao;
import hei.devweb.traderz.entities.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe contenant l'implémentation de l'ensemble des méthodes concernant l'utilisateur

public class UserDaoImpl implements UserDao{

    public User CreateUserFromEmail (String email ){

        String query = "SELECT * FROM personne WHERE email = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){

                    return new User (
                            resultSet.getInt("id"),
                            resultSet.getString("nom_personne"),
                            resultSet.getString("prenom_personne"),
                            resultSet.getString("email"),
                            resultSet.getString("sexe"),
                            resultSet.getString("mot_passe"),
                            resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Méthode permettant de récupperer le mot de passe corespondant associer à un pseudo utilisateur
     * @param email String contenant le pseudo
     * @return String contenant le mot de passe associé au pseudo de l'user
     */
    public String getStoredPassword(String email) {
        String password = null;
        String query = "SELECT mot_passe FROM personne WHERE email = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = resultSet.getString("mot_passe");
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
     * Methode permettant de créer un nouvel utilisateur
     * @param newUser  objet user qui sera crée dans la base de données
     * @return  retourne l'objet user avec son identifiant si l'objet à bien été ajouté dans les tables , retourne null sinon
     */
    public User addUser(User newUser){
        String query ="INSERT INTO personne(nom_personne,prenom_personne,email,sexe,mot_passe,role)\n" +
                "VALUES(?,?,?,?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,newUser.getNom());
            statement.setString(2,newUser.getPrenom());
            statement.setString(3,newUser.getMail());
            statement.setString(4,newUser.getSexe());
            statement.setString(5,newUser.getMdp());
            statement.setString(6,newUser.getRole());
            System.out.println(statement);
            statement.executeUpdate();
            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    newUser.setIdUser(generatedId);
                    return newUser;
                }
            }
        }catch (SQLException e){
            throw  new RuntimeException("/!\\ We get an error trying to add a User /!\\",e);
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
     * Methode supprimant les informations d'un utilisateurs dans la table
     * @param id_user Integer contenant le pseudo de l'utilisateur à supprimer
     */
    public void TurnAdminToUser(Integer id_user){
        String query = "UPDATE personne\n" +
                "SET personne.role = 'user'\n" +
                "WHERE personne.id = " + id_user;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TurnUserToAdmin(Integer id_user){
        String query = "UPDATE personne\n" +
                "SET personne.role = 'admin'\n" +
                "WHERE personne.id = " + id_user;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean IsEmailAlreadyTaken(String email){
//        List<Favori> liste_favoris = new ArrayList<>();

        String query = "SELECT \n" +
                "\tCASE\n" +
                "\t\tWHEN EXISTS (\n" +
                "\t\t\tSELECT 1\n" +
                "\t\t\tFROM personne p\n" +
                "\t\t\tWHERE p.email= '" + email + "'\n" +
                "\n" +
                "\t) THEN TRUE\n" +
                "\tELSE FALSE\n" +
                "\tEND as user_is_created";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Boolean res = resultSet.getBoolean("user_is_created");
                    return res;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> GetAllAdmin() {

        List<User> list_of_admin = new ArrayList<>();

        String query = "SELECT \n" +
                "\t*\n" +
                "FROM personne\n" +
                "WHERE personne.role = 'admin'";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    list_of_admin.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("nom_personne"),
                            resultSet.getString("prenom_personne"),
                            resultSet.getString("email"),
                            resultSet.getString("sexe"),
                            resultSet.getString("mot_passe"),
                            resultSet.getString("role")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_of_admin;
    }

}
