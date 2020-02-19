package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.UserDao;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.User;

import java.util.List;

public class UserManager {


    private static class UserManagerHolder {
        private static UserManager instance = new UserManager();
    }

    public static UserManager getInstance() {
        return UserManagerHolder.instance;
    }

    private UserDaoImpl userDao = new UserDaoImpl();


// Methode permettant de verifier que le mot de passe correspond bien à l'utilisateur entré

    public boolean confirmPassword(String email, String password) throws Exception{
        if (email == null || email.equals("")){
            throw new IllegalArgumentException("Email must be filled!");
        }
        if (password == null || password.equals("")){
            throw new IllegalArgumentException("Password must be filled!");
        }
        String storedPassword = new UserDaoImpl().getStoredPassword(email);
        if (storedPassword == null){
            throw new IllegalArgumentException("Unknown email!");
        }
        return userDao.getStoredPassword(email).equals(password);
    }

// Methode permettant de verifier que 2 champs sont bien egaux

    public boolean verifyNewPassword (String newPassword, String confirmNewPassword){
        if ( newPassword == null || newPassword.equals("")){
            throw new IllegalArgumentException("You must fill the blank to get a new password !");
        }
        if (confirmNewPassword == null ||confirmNewPassword.equals("")){
            throw new IllegalArgumentException("You must fill both of the blank to get a new password !");
        }
        return newPassword.equals(confirmNewPassword);
    }

    public void supprimerUser(String pseudo) {userDao.supprimerUser(pseudo);}

    public void TurnAdminToUser(Integer id_user){userDao.TurnAdminToUser(id_user);}

    public void TurnUserToAdmin(Integer id_user){userDao.TurnUserToAdmin(id_user);}

    public void AddQuestionnairePartieInfoPerso(Integer id_user, Integer sexe, Integer age, String situation,
                                                String domaine, String diplome, Boolean parent, String commune,
                                                String mot1, String mot2, String mot3){
        userDao.AddQuestionnairePartieInfoPerso(id_user, sexe, age, situation,
                                                domaine, diplome, parent, commune,
                                                mot1, mot2, mot3);
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
    ){ userDao.AddQuestionnairePartieSensationSalle(
            id_salle,
            id_user,
            id_sensor,
            q_air_hei,
            dist_fenetre,
            dist_ventilo,
            climat_salle,
            temp_sensation,
            capteur_temp,
            air_sensation,
            air_agreable,
            odeur,
            poussiere,
            symptomes,
            q_air_salle
            );}


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
        userDao.AddQuestionnairePartieInfoSensibilisation(
                id_user, q_air_quartier, pollution, q_air_general, prev_quotidien_q_air_ext, effet_pollution_air_sante,
                recommandation_proteger_pollution_quotidienne, pics_pollution, precautions_pic_pollution,
                sources_pollution_air_inter, sources_pollution_air_exter, moyens_air_sain_inter, moyens_air_sain_exter,
                actions_publiques_ameliorer_qualite_air, saison_pollue, impact_sante, impact_air_pollue_organe,
                aeration_logement, frequence_aeration_logement, eviter_trafic_velo, sport, sport_route_trafic,
                remarques
        );
    }

    public User CreateUserFromEmail (String email ){return userDao.CreateUserFromEmail(email);}

    public Boolean IsEmailAlreadyTaken(String email){return userDao.IsEmailAlreadyTaken(email);}

    public User addUser(User newUser){return userDao.addUser(newUser);}

    public List<User> GetAllAdmin(){return userDao.GetAllAdmin();}
}


