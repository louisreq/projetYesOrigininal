package hei.devweb.traderz.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
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

    public void AddQuestionnairePartieInfoPerso(Integer id_user, Integer sexe, Integer age, String situation, String domaine, String diplome, Boolean parent, String commune, String mot1, String mot2, String mot3){
        String query = "INSERT INTO enquete(id_user, sexe, age, situation, domaine, diplome, parent, commune, mot1, mot2, mot3)\n" +
                "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, id_user);
            statement.setInt(2, sexe);
            statement.setInt(3, age);
            statement.setString(4, situation);
            statement.setString(5, domaine);
            statement.setString(6, diplome);
            statement.setBoolean(7, parent);
            statement.setString(8, commune);
            statement.setString(9, mot1);
            statement.setString(10, mot2);
            statement.setString(11, mot3);

            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddQuestionnairePartieSensationSalle(
            Integer id_salle,
            Integer id_user,
            Integer id_sensor,
            String q_air_hei,
            String dist_fenetre,
            String dist_ventilo,
            String climat_salle,
            String temp_sensation,
            String capteur_temp,
            String air_sensation,
            String air_agreable,
            String odeur,
            Boolean poussiere,
            String symptomes,
            String q_air_salle
            ){


        String query = "INSERT INTO sensation_salles(id_salle, id_user, id_sensor, q_air_hei," +
                "dist_fenetre, dist_ventilo, climat_salle, temp_sensation, capteur_temp, air_sensation," +
                "air_agreable, odeur, poussiere, symptomes, q_air_salle)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, id_salle);
            statement.setInt(2, id_user);
            statement.setInt(3, id_sensor);
            statement.setString(4, q_air_hei);
            statement.setString(5, dist_fenetre);
            statement.setString(6, dist_ventilo);
            statement.setString(7, climat_salle);
            statement.setString(8, temp_sensation);
            statement.setString(9, capteur_temp);
            statement.setString(10, air_sensation);
            statement.setString(11, air_agreable);
            statement.setString(12, odeur);
            statement.setBoolean(13, poussiere);
            statement.setString(14, symptomes);
            statement.setString(15, q_air_salle);

            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void AddQuestionnairePartieInfoSensibilisation(
            Integer id_user,
            String q_air_quartier,
            Boolean pollution,
            Boolean q_air_general,
            Boolean prev_quotidien_q_air_ext,
            Boolean effet_pollution_air_sante,
            Boolean recommandation_proteger_pollution_quotidienne,
            Boolean pics_pollution,
            Boolean precautions_pic_pollution,
            Boolean sources_pollution_air_inter,
            Boolean sources_pollution_air_exter,
            Boolean moyens_air_sain_inter,
            Boolean moyens_air_sain_exter,
            Boolean actions_publiques_ameliorer_qualite_air,
            String saison_pollue,
            String impact_sante,
            String impact_air_pollue_organe,
            Boolean aeration_logement,
            String frequence_aeration_logement,
            String eviter_trafic_velo,
            Boolean sport,
            Boolean sport_route_trafic,
            String remarques
    ){
        String query = "INSERT INTO info_sensibilisation(" +
                "id_user, q_air_quartier, pollution, q_air_general, prev_quotidien_q_air_ext," +
                "effet_pollution_air_sante, recommandation_proteger_pollution_quotidienne, pics_pollution," +
                "precautions_pic_pollution, sources_pollution_air_inter, sources_pollution_air_exter," +
                "moyens_air_sain_inter, moyens_air_sain_exter, actions_publiques_ameliorer_qualite_air," +
                "saison_pollue, impact_sante, impact_air_pollue_organe, aeration_logement," +
                "frequence_aeration_logement, eviter_trafic_velo, sport, sport_route_trafic, remarques)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, id_user);
            statement.setString(2, q_air_quartier);
            statement.setBoolean(3, pollution);
            statement.setBoolean(4, q_air_general);
            statement.setBoolean(5, prev_quotidien_q_air_ext);
            statement.setBoolean(6, effet_pollution_air_sante);
            statement.setBoolean(7, recommandation_proteger_pollution_quotidienne);
            statement.setBoolean(8, pics_pollution);
            statement.setBoolean(9, precautions_pic_pollution);
            statement.setBoolean(10, sources_pollution_air_inter);
            statement.setBoolean(11, sources_pollution_air_exter);
            statement.setBoolean(12, moyens_air_sain_inter);
            statement.setBoolean(13, moyens_air_sain_exter);
            statement.setBoolean(14, actions_publiques_ameliorer_qualite_air);
            statement.setString(15, saison_pollue);
            statement.setString(16, impact_sante);
            statement.setString(17, impact_air_pollue_organe);
            statement.setBoolean(18, aeration_logement);
            statement.setString(19, frequence_aeration_logement);
            statement.setString(20, eviter_trafic_velo);
            statement.setBoolean(21, sport);
            statement.setBoolean(22, sport_route_trafic);
            statement.setString(23, remarques);

            System.out.println(statement);
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
