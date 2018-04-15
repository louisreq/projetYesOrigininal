package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.TransactionDao;
import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.Transaction;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CotationManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    /**
     * Ajoute à la BDD une nouvelle transaction dont le sens vaut 0 --> ACHETER
     * @param user objet utilisateur qui contient les infos de l'utilisateur qui réalise l'achat
     * @param cotation objet cotation contenant les informations relative à la cotation
     * @param volume double contenant le volume de cotation voulus
     */
    // Ajoute à la BDD une nouvelle transaction dont le sens vaut 0 --> ACHETER
    public void AcheterTransac (User user, Cotation cotation, Double volume){
        String query1 = "INSERT INTO `transactions` (`transac_user_pseudo`,`transac_volume`,`transac_cotation_categorie`,`transac_cotation_id`,`transac_cotation_prix`,`transac_cotation_nom`,`transac_sens`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, false)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,user.getIdentifiant());
            statement.setDouble(2,volume);
            statement.setString(3,cotation.getCategorie());
            statement.setInt(4,cotation.getIdCotation());
            statement.setDouble(5,cotation.getPrix());
            statement.setString(6,cotation.getCotationNom());
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
    }



    /**
     * Ajoute à la BDD une nouvelle transaction dont le sens vaut 1 --> VENDRE
     * @param user objet utilisateur qui contient les infos de l'utilisateur qui réalise la vente
     * @param cotation objet cotation contenant les informations relative à la cotation
     * @param volume double contenant le volume de cotation voulus
     */
    public void VendreTransac (User user, Cotation cotation, Double volume){
        String query1 = "INSERT INTO `transactions` (`transac_user_pseudo`,`transac_volume`,`transac_cotation_categorie`,`transac_cotation_id`,`transac_cotation_prix`,`transac_cotation_nom`,`transac_sens`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, true)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,user.getIdentifiant());
            statement.setDouble(2,volume);
            statement.setString(3,cotation.getCategorie());
            statement.setInt(4,cotation.getIdCotation());
            statement.setDouble(5,cotation.getPrix());
            statement.setString(6,cotation.getCotationNom());
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
    }

    /*
     * Void AcheterHisto: permet de rajouter une transaction dans la table historique
      *
      * @param user: utilisateur connecté
      * @param cotation: Cotation que l'on veut acheter
      * @param volule : le nombre de cotation acheté
      *
     */


    /**
     * Ajout d'une transaction dans la table historique lorsque cette dernière est cloturée
     * @param transaction objet transaction contenant les informations de la transaction cloturée
     */
    @Override
    public void Revendre(Transaction transaction) {
        String query = "INSERT INTO `historiques` (`histo_user_pseudo`,`histo_volume`,`histo_cotation_categorie`,`histo_cotation_id`,`histo_cotation_prix`,`histo_cotation_nom`,`histo_sens`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, false)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,transaction.getTransacUserPseudo());
            statement.setDouble(2,transaction.getTransacVolume());
            statement.setString(3,transaction.getTransacCotationCategorie());
            statement.setInt(4,transaction.getTransacCotationId());
            statement.setDouble(5,transaction.getTransacPrix());
            statement.setString(6,transaction.getTransacCotationNom());
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
    }

    /**
     * Ajout d'une transaction dans la table historique lorsque cette dernière est cloturée
     * @param transaction objet transaction contenant les informations de la transaction cloturée
     */
    public void Racheter(Transaction transaction) {
        String query = "INSERT INTO `historiques` (`histo_user_pseudo`,`histo_volume`,`histo_cotation_categorie`,`histo_cotation_id`,`histo_cotation_prix`,`histo_cotation_nom`,`histo_sens`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, true)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,transaction.getTransacUserPseudo());
            statement.setDouble(2,transaction.getTransacVolume());
            statement.setString(3,transaction.getTransacCotationCategorie());
            statement.setInt(4,transaction.getTransacCotationId());
            statement.setDouble(5,transaction.getTransacPrix());
            statement.setString(6,transaction.getTransacCotationNom());
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
    }

    /*
   *Methode qui permet de retirer une transaction de la table 'transactions' qand on renseigne son id
   *
   * @param idTransac : Identifiant de la transaction à supprimer (il est récuperer grâce à un formulaire hidden)
   *
     */
    public void DeleteTransac (Integer idTransac){
        String query = "DELETE FROM transactions WHERE transac_id = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,idTransac );
            statement.executeUpdate();
        }catch (SQLException e){
            throw  new RuntimeException("Error when getting user",e);
        }
    }


    /**
     * Methode creant une liste d'objets transaction en fonction du pseudo d'un utilisateur
     * @param username String contenant le pseudo d'un utilisateur
     * @return retourne la liste d'objets transaction
     */
    public List<Transaction> listTransacByUser (String username){
        List<Transaction> transactions= new ArrayList<>();
        String query = "SELECT * FROM transactions INNER JOIN cotations ON transactions.transac_cotation_nom=cotations.cotation_nom WHERE transac_user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(new Transaction(
                            resultSet.getInt("transac_id"),
                            resultSet.getString("transac_user_pseudo"),
                            resultSet.getString("transac_cotation_categorie"),
                            resultSet.getString("transac_cotation_nom"),
                            resultSet.getInt("transac_cotation_id"),
                            resultSet.getDouble("transac_cotation_prix"),
                            resultSet.getDouble("transac_volume"),
                            resultSet.getBoolean("transac_sens"),
                            (resultSet.getDouble("cotation_prix")-resultSet.getDouble("transac_cotation_prix"))*resultSet.getDouble("transac_volume"),
                            resultset.getDouble("cotation-varjour")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Metgode recuperant les informations liées à une transaction dans la table transactions grâce à son id
     * @param id Integer contenant l'identifiant de la transaction que l'on souhaite obtenir
     * @return retourne un objet Transaction
     */
    public Transaction CreateTransacFromId (Integer id) {
        String query = "SELECT * FROM transactions INNER JOIN cotations ON transactions.transac_cotation_nom=cotations.cotation_nom WHERE transac_id = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){

                    return new Transaction(
                            resultSet.getInt("transac_id"),
                            resultSet.getString("transac_user_pseudo"),
                            resultSet.getString("transac_cotation_categorie"),
                            resultSet.getString("transac_cotation_nom"),
                            resultSet.getInt("transac_cotation_id"),
                            resultSet.getDouble("transac_cotation_prix"),
                            resultSet.getDouble("transac_volume"),
                            resultSet.getBoolean("transac_sens"),
                            (resultSet.getDouble("cotation_prix")-resultSet.getDouble("transac_cotation_prix"))*resultSet.getDouble("transac_volume"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retourne une liste d'objets transaction contenant les informations des transactions cloturées
     * @param username String contenant le pseudo de l'utilisateur
     * @return retourne une liste d'objets transaction
     */
    public List<Transaction> HistoriqueTransacByUser (String username){
        List<Transaction> transactions= new ArrayList<>();
        String query = "SELECT * FROM historiques INNER JOIN cotations ON historiques.histo_cotation_nom=cotations.cotation_nom WHERE histo_user_pseudo = ?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(new Transaction(
                            resultSet.getInt("histo_id"),
                            resultSet.getString("histo_user_pseudo"),
                            resultSet.getString("histo_cotation_categorie"),
                            resultSet.getString("histo_cotation_nom"),
                            resultSet.getInt("histo_cotation_id"),
                            resultSet.getDouble("histo_cotation_prix"),
                            resultSet.getDouble("histo_volume"),
                            resultSet.getBoolean("histo_sens"),
                            (resultSet.getDouble("cotation_prix")-resultSet.getDouble("histo_cotation_prix"))*resultSet.getDouble("histo_volume")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


}

