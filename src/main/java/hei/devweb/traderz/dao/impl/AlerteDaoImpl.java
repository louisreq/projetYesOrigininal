package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.AlerteDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Alerte;

//import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlerteDaoImpl implements AlerteDao {

    public void AddAlerte (Object datetime, String message, Integer personne_id, Integer salle_id, String titre, Boolean checked){
        String query = "INSERT INTO `alerte`(`date`, `message`, `personne_id`, `salle_id`, `titre`, `checked`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setObject(1, datetime);
            statement.setString(2, message);
            statement.setInt(3, personne_id);
            statement.setInt(4, salle_id);
            statement.setString(5, titre);
            statement.setBoolean(6, checked);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Add this ALERT /!\\/!\\ ");
        }
    }


    public void SetAlerteChecked (Integer id_alerte, Boolean checked){
        String query = "UPDATE alerte\n" +
                "SET checked = ? \n" +
                "WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setBoolean(1, checked);
            statement.setInt(2, id_alerte);

            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("/!\\/!\\ Error, cannot Set Checked for the Alerte/!\\/!\\ ");
        }
    }

    public List<Alerte> GetAlertesFromUserId(Integer user_id){
        List<Alerte> liste_alerte = new ArrayList<>();
        String query = "select\n" +
                "\talerte.id as id_alerte,\n" +
                "    alerte.date as date_alerte,\n" +
                "    alerte.message as message,\n" +
                "    alerte.personne_id as id_personne,\n" +
                "    alerte.salle_id as id_salle,\n" +
                "    alerte.titre as titre,\n" +
                "    alerte.checked as checked\n" +
                "\n" +
                "from alerte\n" +
                "\n" +
                "where alerte.personne_id = " + user_id;
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_alerte.add(new Alerte(
                            resultSet.getInt("id_alerte"),
                            resultSet.getTimestamp("date_alerte"),
                            resultSet.getString("message"),
                            resultSet.getInt("id_personne"),
                            resultSet.getInt("id_salle"),
                            resultSet.getString("titre"),
                            resultSet.getBoolean("checked")
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste_alerte;
    }

    public List<Alerte> GetAllAlertes(String user_input){

        String QueryLikeStatement = GetQueryLikeStatementFromUserInput(SplitUserInput(user_input));

        List<Alerte> liste_alerte = new ArrayList<>();
        String query = "select\n" +
                "    alerte.id as id_alerte,\n" +
                "    alerte.date as date_alerte,\n" +
                "    alerte.message as message,\n" +
                "    alerte.salle_id as id_salle,\n" +
                "    alerte.titre as titre,\n" +
                "    alerte.checked as checked,\n" +
                "    concat( p.nom_personne, \" \", p.prenom_personne ) as nom" +
                "\n" +
                "from alerte\n" +
                "INNER JOIN personne p ON (alerte.personne_id = p.id)\n" +
                "INNER JOIN salle ON (alerte.salle_id = salle.id)\n" +
                "INNER JOIN etage ON (salle.etage_id = etage.id)\n" +
                "INNER JOIN batiment ON (etage.batiment_id = batiment.id)\n" +
                "INNER JOIN campus ON (batiment.campus_id = campus.id)\n" +
                "WHERE 1=1 \n" +
                QueryLikeStatement
                ;
        System.out.println(query);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_alerte.add(new Alerte(
                            resultSet.getInt("id_alerte"),
                            resultSet.getTimestamp("date_alerte"),
                            resultSet.getString("message"),
                            resultSet.getInt("id_salle"),
                            resultSet.getString("titre"),
                            resultSet.getBoolean("checked"),
                            resultSet.getString("nom")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste_alerte;
    }


    public List<String> SplitUserInput(String user_input){
        List<String> splitted_input;
        splitted_input = Arrays.asList(user_input.split(" "));
        return splitted_input;
    }

    public String GetQueryLikeStatementFromUserInput(List<String> splitted_input){
        String queryLikeStatement = "";
        for(String word_input : splitted_input){
            word_input = word_input.replace(" ", "");
            queryLikeStatement += "  AND ( \n batiment.nom_batiment LIKE " + "'%" + word_input + "%'\n";
            queryLikeStatement += " OR etage.nom_etage LIKE " + "'%" + word_input + "%'\n";
            queryLikeStatement += " OR salle.nom_salle LIKE " + "'%" + word_input + "%'\n";
            queryLikeStatement += " OR p.nom_personne LIKE " +  "'%" + word_input + "%'\n";
            queryLikeStatement += " OR alerte.titre LIKE " +  "'%" + word_input + "%'\n";
            queryLikeStatement += " OR alerte.message LIKE " +  "'%" + word_input + "%'\n";
            queryLikeStatement += " OR alerte.date LIKE " +  "'%" + word_input + "%'\n";
            queryLikeStatement += " OR p.prenom_personne LIKE " +  "'%" + word_input + "%'\n)";
        }

        return queryLikeStatement;
    }
//
//    public List<Alerte> GetListOfSalleFromCampusAndUserInput(Integer id_campus, String user_input) {
////        On s'occupe de traiter la saisie de l'utilisateur d'abord
//        String QueryLikeStatement = GetQueryLikeStatementFromUserInput(SplitUserInput(user_input));
//
//
//        List<Alerte> liste_alerte = new ArrayList<>();
//
//
//        String query = "SELECT\n" +
//                "salle.id as id_salle,\n" +
//                "salle.nom_salle as nom_salle,\n" +
//                "etage.id as id_etage,\n" +
//                "etage.nom_etage as nom_etage,\n" +
//                "batiment.id as id_batiment,\n" +
//                "batiment.nom_batiment as nom_batiment,\n" +
//                "campus.id as id_campus\n" +
//                "\n" +
//                "\n" +
//                "FROM campus\n" +
//                "INNER JOIN batiment ON (campus.id = batiment.campus_id)\n" +
//                "INNER JOIN etage ON (batiment.id = etage.batiment_id)\n" +
//                "INNER JOIN salle ON (etage.id = salle.etage_id)\n" +
//                "WHERE campus.id = " + id_campus + "\n" +
//                QueryLikeStatement;
//        System.out.println(query);
//        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
//             PreparedStatement statement = (connection).prepareStatement(query)) {
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//
//                    liste_alerte.add(new Alerte(
//                            resultSet.getInt("id_salle"),
//                            resultSet.getString("nom_salle"),
//                            resultSet.getInt("id_etage"),
//                            resultSet.getString("nom_etage"),
//                            resultSet.getInt("id_batiment"),
//                            resultSet.getString("nom_batiment"),
//                            resultSet.getInt("id_campus")));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return liste_alerte;
//    }

}
