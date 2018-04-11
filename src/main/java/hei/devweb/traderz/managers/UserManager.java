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

    public boolean userValid (String nomUser){
        /*if (userDao.UserDontExist(nomUser)){
            throw new IllegalArgumentException("This username already exist !");
        }*/
        return userDao.UserDontExist(nomUser);
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

    public void supprimerUser(String pseudo) {         userDao.supprimerUser(pseudo);   }

    public User CreateUserFromPseudo (String pseudo ){return userDao.CreateUserFromPseudo(pseudo);}


}


