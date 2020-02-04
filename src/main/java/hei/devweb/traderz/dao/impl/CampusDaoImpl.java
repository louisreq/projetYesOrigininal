package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.CampusDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CampusDaoImpl implements CampusDao {

    public List<Campus> GetListOfCampus() {
        List<Campus> liste_campus = new ArrayList<>();
        String query = "SELECT\n" +
                "    campus.id as id_campus,\n" +
                "    campus.nom_campus as nom_campus, \n" +
                "null as liste_salles" +
                "\n" +
                "FROM campus\n" +
                "GROUP BY campus.id";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_campus.add(new Campus(
                            resultSet.getInt("id_campus"),
                            resultSet.getString("nom_campus"),
                            null));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste_campus;
    }

    public List<Salle> GetListOfSalleWithCampusId(Integer campus_id){
        List<Salle> liste_salle = new ArrayList<>();
        String query = "SELECT\n" +
                "    salle.id as id_salle,\n" +
                "    salle.nom_salle as nom_salle,\n" +
                "    etage.id as id_etage,\n" +
                "    etage.nom_etage as nom_etage,\n" +
                "    batiment.id as id_batiment,\n" +
                "    batiment.nom_batiment as nom_batiment,\n" +
                "    campus.id as id_campus,\n" +
                "    campus.nom_campus as nom_campus\n" +
                "\n" +
                "\n" +
                "FROM campus\n" +
                "INNER JOIN batiment ON (campus.id = batiment.campus_id)\n" +
                "INNER JOIN etage ON (batiment.id = etage.batiment_id)\n" +
                "INNER JOIN salle ON (etage.id = salle.etage_id)\n" +
                "WHERE campus.id = " + campus_id + "\n" +
                "ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    liste_salle.add(new Salle(
                            resultSet.getInt("id_salle"),
                            resultSet.getString("nom_salle"),
                            resultSet.getInt("id_etage"),
                            resultSet.getString("nom_etage"),
                            resultSet.getInt("id_batiment"),
                            resultSet.getString("nom_batiment"),
                            resultSet.getInt("id_campus")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste_salle;
    }

    public Campus GetListOfSalleWithCampusName(String name_campus){
        Campus campus = new Campus(null, null, null) ;
        List<Salle> liste_salle = new ArrayList<>();
        String query = "SELECT\n" +
                "    salle.id as id_salle,\n" +
                "    salle.nom_salle as nom_salle,\n" +
                "    etage.id as id_etage,\n" +
                "    etage.nom_etage as nom_etage,\n" +
                "    batiment.id as id_batiment,\n" +
                "    batiment.nom_batiment as nom_batiment,\n" +
                "    campus.id as id_campus,\n" +
                "    campus.nom_campus as nom_campus\n" +
                "\n" +
                "\n" +
                "FROM campus\n" +
                "INNER JOIN batiment ON (campus.id = batiment.campus_id)\n" +
                "INNER JOIN etage ON (batiment.id = etage.batiment_id)\n" +
                "INNER JOIN salle ON (etage.id = salle.etage_id)\n" +
                "WHERE campus.nom_campus = " + name_campus + "\n" +
                "ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    campus = new Campus(
                            resultSet.getInt("id_campus"),
                            resultSet.getString("nom_campus"),
                            null
                    );

                    liste_salle.add(new Salle(
                            resultSet.getInt("id_salle"),
                            resultSet.getString("nom_salle"),
                            resultSet.getInt("id_etage"),
                            resultSet.getString("nom_etage"),
                            resultSet.getInt("id_batiment"),
                            resultSet.getString("nom_batiment"),
                            resultSet.getInt("id_campus")));
                    campus.setList_salles(liste_salle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campus;
    }

    public Campus GetCampusFromSalleId(Integer id_salle) {
        Campus campus;
        List<Salle> liste_salle = new ArrayList<>();
        String query = "SELECT\n" +
                "    salle.id as id_salle,\n" +
                "    salle.nom_salle as nom_salle,\n" +
                "    etage.id as id_etage,\n" +
                "    etage.nom_etage as nom_etage,\n" +
                "    batiment.id as id_batiment,\n" +
                "    batiment.nom_batiment as nom_batiment,\n" +
                "    campus.id as id_campus,\n" +
                "    campus.nom_campus as nom_campus\n" +
                "\n" +
                "\n" +
                "FROM campus\n" +
                "INNER JOIN batiment ON (campus.id = batiment.campus_id)\n" +
                "INNER JOIN etage ON (batiment.id = etage.batiment_id)\n" +
                "INNER JOIN salle ON (etage.id = salle.etage_id)\n" +
                "\n" +
                "WHERE salle.id = " + id_salle + " \n" +
                "ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    campus = new Campus(
                            resultSet.getInt("id_campus"),
                            resultSet.getString("nom_campus"),
                            null
                    );

//                    campus.setList_salles(
                    liste_salle.add(
                            new Salle(
                                    resultSet.getInt("id_salle"),
                                    resultSet.getString("nom_salle"),
                                    resultSet.getInt("id_etage"),
                                    resultSet.getString("nom_etage"),
                                    resultSet.getInt("id_batiment"),
                                    resultSet.getString("nom_batiment"),
                                    resultSet.getInt("id_campus")
                            ));
                    campus.setList_salles(liste_salle);
                    return campus;
        }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
