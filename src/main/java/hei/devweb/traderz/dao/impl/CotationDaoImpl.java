package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.CotationDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.entities.Cotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CotationDaoImpl implements CotationDao {

    //Retourne un objet Cotation à partir de su identifiant
    public Cotation CreateCotationFromId (Integer id){

        String query = "SELECT * FROM cotations WHERE cotation_id = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){

                    return new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cotation> listCotationAD(){
        List<Cotation> cotations = new ArrayList<>();
        String query = "SELECT * FROM cotations WHERE cotation_nom LIKE 'A%' OR cotation_nom LIKE 'B%' OR cotation_nom LIKE 'C%' OR cotation_nom LIKE 'D%'";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cotations.add(new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotations;
    }

    public List<Cotation> listCotationEG(){
        List<Cotation> cotations = new ArrayList<>();
        String query = "SELECT * FROM cotations WHERE cotation_nom LIKE 'E%' OR cotation_nom LIKE 'F%' OR  cotation_nom LIKE 'G%'";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cotations.add(new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotations;
    }


    public List<Cotation> listCotationHL(){
        List<Cotation> cotations = new ArrayList<>();
        String query = "SELECT * FROM cotations WHERE cotation_nom LIKE 'H%' OR cotation_nom LIKE 'I%'OR cotation_nom LIKE 'J%' OR cotation_nom LIKE 'K%' OR cotation_nom LIKE 'L%'";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cotations.add(new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotations;
    }

    public List<Cotation> listCotationMP(){
        List<Cotation> cotations = new ArrayList<>();
        String query = "SELECT * FROM cotations WHERE cotation_nom LIKE 'M%' OR cotation_nom LIKE 'N%'OR cotation_nom LIKE 'O%' OR cotation_nom LIKE 'P%'";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cotations.add(new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotations;
    }

    public List<Cotation> listCotationQT(){
        List<Cotation> cotations = new ArrayList<>();
        String query = "SELECT * FROM cotations WHERE cotation_nom LIKE 'Q%' OR cotation_nom LIKE 'R%'OR cotation_nom LIKE'S%' OR cotation_nom LIKE'T'";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cotations.add(new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotations;
    }

    public List<Cotation> listCotationUZ(){
        List<Cotation> cotations = new ArrayList<>();
        String query = "SELECT * FROM cotations WHERE cotation_nom LIKE 'U%' OR cotation_nom LIKE 'V%' OR cotation_nom LIKE 'W%' OR cotation_nom LIKE 'X%' OR cotation_nom LIKE 'Y%' OR cotation_nom LIKE'Z%'";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cotations.add(new Cotation(
                            resultSet.getInt("cotation_id"),
                            resultSet.getString("cotation_nom"),
                            resultSet.getString("cotation_categorie"),
                            resultSet.getDouble("cotation_prix"),
                            resultSet.getDouble("cotation_haut"),
                            resultSet.getDouble("cotation_bas"),
                            resultSet.getDouble("cotation_varjour"),
                            resultSet.getDouble("cotation_veille"),
                            resultSet.getDouble("cotation_ouverture"),
                            resultSet.getInt("cotation_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotations;
    }
}
