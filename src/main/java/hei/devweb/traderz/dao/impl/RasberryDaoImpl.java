package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.RasberryDao;
import hei.devweb.traderz.entities.Rasberry;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RasberryDaoImpl implements RasberryDao {

    public List<Rasberry> GetAllRasberrySalle(){
        List<Rasberry> liste_alerte = new ArrayList<>();
        String query = "SELECT * FROM capteur_salle WHERE isnull(date_fin)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_alerte.add(new Rasberry(
                            resultSet.getInt("id"),
                            resultSet.getString("nom_capteur"),
                            resultSet.getInt("salle_id"),
                            resultSet.getString("position"),
                            resultSet.getTimestamp("date_debut"),
                            resultSet.getTimestamp("date_fin")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste_alerte;
    }

    public Map<Integer, String> GetAllRasberry(){
//        List<Rasberry> liste_alerte = new ArrayList<>();
        Map<Integer, String> mapping = new HashMap<Integer, String>();

        String query = "SELECT * FROM capteur WHERE is_deleted=0";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    mapping.put(
                            resultSet.getInt("id"),
                            resultSet.getString("nom_capteur")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mapping;
    }


    public void AddRasberry (String nom_rasberry){
        String query = "INSERT INTO `capteur`(`nom_capteur`)\n" +
                "VALUE (?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, nom_rasberry);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Add this Capteur /!\\/!\\ ");
        }
    }

    public void AddRasberrySalle (Rasberry rasberry){
        String query = "INSERT INTO `capteur_salle`(`nom_capteur`, `position`, `salle_id`, `date_debut`)\n" +
                "VALUE (?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, rasberry.getNom_rasberry());
            statement.setString(2, rasberry.getEmplacement());
            statement.setInt(3, rasberry.getId_salle());
            statement.setTimestamp(4, rasberry.getDate_debut());

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Add this Capteur /!\\/!\\ ");
        }
    }

    public void SetDateFinRasberrySalle (Rasberry rasberry){
        String query = "UPDATE capteur_salle\n" +
                "SET date_fin = ? \n" +
                "WHERE nom_capteur=? AND isnull(date_fin)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setTimestamp(1, rasberry.getDate_fin());
            statement.setString(2, rasberry.getNom_rasberry());

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Set Date de fin our le capteur/!\\/!\\ ");
        }
    }

    public void DeleteRasberry (Rasberry rasberry){
        String query = "UPDATE capteur\n" +
                "SET is_deleted = 1 \n" +
                "WHERE nom_capteur = ? ";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, rasberry.getNom_rasberry());

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Delete Capteur/!\\/!\\ ");
        }
    }

    public void EditRasberry (String old_name, String new_name){
        String query = "UPDATE capteur\n" +
                "SET nom_capteur = ? \n" +
                "WHERE nom_capteur = ? ";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, new_name);
            statement.setString(2, old_name);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Delete Capteur/!\\/!\\ ");
        }
    }

    public void EditRasberrySalleName (String old_name, String new_name){
        String query = "UPDATE capteur_salle\n" +
                "SET nom_capteur = ? \n" +
                "WHERE nom_capteur = ? ";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, new_name);
            statement.setString(2, old_name);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Delete Capteur/!\\/!\\ ");
        }
    }

    public Boolean IsCapteurNameAlreadyTaken(String rasberry_name){
//        List<Favori> liste_favoris = new ArrayList<>();

        String query = "SELECT \n" +
                "\tCASE\n" +
                "\t\tWHEN EXISTS (\n" +
                "\t\t\tSELECT 1\n" +
                "\t\t\tFROM capteur c\n" +
                "\t\t\tWHERE c.nom_capteur= '" + rasberry_name + "'\n" +
                "\n" +
                "\t) THEN TRUE\n" +
                "\tELSE FALSE\n" +
                "\tEND as rasberry_exists";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Boolean res = resultSet.getBoolean("rasberry_exists");
                    return res;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
