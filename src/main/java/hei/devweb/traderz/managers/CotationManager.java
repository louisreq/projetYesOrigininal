package hei.devweb.traderz.managers;

import hei.devweb.traderz.dao.CotationDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.impl.CotationDaoImpl;
import hei.devweb.traderz.entities.Cotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CotationManager {

    private static class CotationManagerHolder {
        private static CotationManager instance = new CotationManager();
    }

    public static CotationManager getInstance() { return CotationManagerHolder.instance;    }


    private CotationDaoImpl cotationDao= new CotationDaoImpl();


    public List<Cotation> listCotationAD(){  return cotationDao.listCotationAD();  }
    public List<Cotation> listCotationEG(){  return cotationDao.listCotationEG();  }
    public List<Cotation> listCotationHL(){  return cotationDao.listCotationHL();  }
    public List<Cotation> listCotationMP(){  return cotationDao.listCotationMP();  }
    public List<Cotation> listCotationQT(){  return cotationDao.listCotationQT();  }
    public List<Cotation> listCotationUZ(){  return cotationDao.listCotationUZ();  }
    public Cotation CreateCotationFromId(Integer id){return cotationDao.CreateCotationFromId(id); }





}
