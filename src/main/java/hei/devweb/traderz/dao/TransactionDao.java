package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.Transaction;
import hei.devweb.traderz.entities.User;

public interface TransactionDao {

    void AcheterTransac (User user, Cotation cotation, Double volume);
    void AcheterHisto (User user, Cotation cotation, Double volume);
    void VendreTransac (User user, Cotation cotation, Double volume);
    void VendreHisto (User user, Cotation cotation, Double volume);
    void Revendre (Transaction transaction);
}
