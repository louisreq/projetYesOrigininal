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

    public boolean confirmPassword(String username, String password) throws Exception{
        if (username == null || username.equals("")){
            throw new IllegalArgumentException("Username must be filled!");
        }
        if (password == null || password.equals("")){
            throw new IllegalArgumentException("Password must be filled!");
        }
        String storedPassword = new UserDaoImpl().getStoredPassword(username);
        if (storedPassword == null){
            throw new IllegalArgumentException("Unknown username!");
        }
        return userDao.getStoredPassword(username).equals(password);
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

//Méthode permettant de verifier qu'un utilisateur a bien été crée

    public  User addUser(User newUser) {
        if (newUser == null) {
            throw new IllegalArgumentException("An user must be provided.");
        }
        if (newUser.getPrenom() == null || "".equals(newUser.getPrenom())) {
            throw new IllegalArgumentException("An user must have a firstname.");
        }
        if (newUser.getNom() == null || "".equals(newUser.getNom())) {
            throw new IllegalArgumentException("An user must have a lastname.");
        }
        if (newUser.getIdentifiant() == null || "".equals(newUser.getIdentifiant())) {
            throw new IllegalArgumentException("An user must have a pseudo.");
        }
        if (newUser.getMdp() == null || "".equals(newUser.getMdp())) {
            throw new IllegalArgumentException("An user must have a password.");
        }
        if (newUser.getMail() == null || "".equals(newUser.getMail())) {
            throw new IllegalArgumentException("A user must have a mail.");
        }
        if (newUser.getDateNaissance() == null || "".equals(newUser.getDateNaissance())) {
            throw new IllegalArgumentException("A user must have a date of birth.");
        }
        if (newUser.getSexe() == null || "".equals(newUser.getSexe())) {
            throw new IllegalArgumentException("A user must have a sex.");
        }

        return userDao.addUser(newUser);
    }

    public List<User> listuserconnecte(String pseudo) {
        return userDao.listuserconnecte(pseudo);
    }

    public void supprimerUser(String pseudo) {         userDao.supprimerUser(pseudo);   }

    public User CreateUserFromPseudo (String pseudo ){return userDao.CreateUserFromPseudo(pseudo);}


}


