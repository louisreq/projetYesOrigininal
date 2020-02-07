package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.User;

public interface UserDao {
        String getStoredPassword(String email);

//        User addUser(User newUser);
        Boolean IsEmailAlreadyTaken(String email);
        User addUser(User newUser);

}
