package hei.devweb.traderz.dao;

import hei.devweb.traderz.entities.Rasberry;

import java.util.List;
import java.util.Map;

public interface RasberryDao {

    public List<Rasberry> GetAllRasberrySalle();
    public Map<Integer, String> GetAllRasberry();

}
