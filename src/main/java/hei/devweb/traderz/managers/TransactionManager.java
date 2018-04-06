package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.impl.TransactionDaoImpl;
import hei.devweb.traderz.entities.Transaction;

public class TransactionManager {

    private static class TransactionManagerHolder {
        private static TransactionManager instance = new TransactionManager();
    }

    public static TransactionManager getInstance() { return TransactionManagerHolder.instance;    }


    private TransactionDaoImpl transactionDao= new TransactionDaoImpl();

    public Transaction CreateTransacFromId (Integer id){return transactionDao.CreateTransacFromId(id);}


}
