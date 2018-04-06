package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.Transaction;
import hei.devweb.traderz.entities.User;

public interface TransactionDao {

    void Acheter (User user, Cotation cotation, Double volume);
    void Vendre (User user, Cotation cotation, Double volume);
    void Revendre (Transaction transaction);
}
