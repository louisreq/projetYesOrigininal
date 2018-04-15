package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.User;

import java.util.List;

public interface AdminDao  {

    List<User> listOfUser () ;
    void DeleteUser (Integer userid);
}
