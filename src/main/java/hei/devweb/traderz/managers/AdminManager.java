package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.AdminDaoImpl;
import hei.devweb.traderz.entities.Admin;

public class AdminManager {

    private static class AdminManagerHolder {
        private static AdminManager instance = new AdminManager();
    }

    public static AdminManager getInstance() {
        return AdminManagerHolder.instance;
    }

    private AdminDaoImpl adminDao = new AdminDaoImpl();

    // Methode permettant de verifier que le mot de passe correspond bien à l'utilisateur entré

    public boolean confirmPassword(String username, String password) throws Exception{
        if (username == null || username.equals("")){
            throw new IllegalArgumentException("Username must be filled!");
        }
        if (password == null || password.equals("")){
            throw new IllegalArgumentException("Password must be filled!");
        }
        String storedPassword = new AdminDaoImpl().getStoredPassword(username);
        if (storedPassword == null){
            throw new IllegalArgumentException("Unknown username!");
        }
        return adminDao.getStoredPassword(username).equals(password);
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


    public Admin CreateAdminFromName (String name ){return adminDao.CreateAdminFromName(name);}
}
