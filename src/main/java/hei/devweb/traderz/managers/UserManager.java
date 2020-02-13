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

    public User CreateUserFromEmail (String email ){return userDao.CreateUserFromEmail(email);}

    public Boolean IsEmailAlreadyTaken(String email){return userDao.IsEmailAlreadyTaken(email);}

    public User addUser(User newUser){return userDao.addUser(newUser);}

    public List<User> GetAllAdmin(){return userDao.GetAllAdmin();}
}


