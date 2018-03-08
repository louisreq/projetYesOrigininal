package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.User;

public interface UserDao {
        String getStoredPassword(String username);

        User addUser(User newUser);

}
