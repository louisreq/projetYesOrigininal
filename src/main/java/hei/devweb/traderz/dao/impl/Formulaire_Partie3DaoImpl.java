package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.Formulaire_Partie3Dao;
import hei.devweb.traderz.entities.Formulaire_Partie3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Formulaire_Partie3DaoImpl implements Formulaire_Partie3Dao {

    public List<Formulaire_Partie3> GetAllFormPartie3() {

        List<Formulaire_Partie3> Liste_form_partie_3 = new ArrayList<>();

        String query = "SELECT * FROM info_sensibilisation GROUP BY id_user";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {


                    Liste_form_partie_3.add(new Formulaire_Partie3(
                            resultSet.getInt("id_info_sensibilisation"),
                            resultSet.getInt("id_user"),
                            resultSet.getString("q_air_quartier"),
                            resultSet.getBoolean("pollution"),
                            resultSet.getBoolean("q_air_general"),
                            resultSet.getBoolean("prev_quotidien_q_air_ext"),
                            resultSet.getBoolean("effet_pollution_air_sante"),
                            resultSet.getBoolean("recommandation_proteger_pollution_quotidienne"),
                            resultSet.getBoolean("pics_pollution"),
                            resultSet.getBoolean("precautions_pic_pollution"),
                            resultSet.getBoolean("sources_pollution_air_inter"),
                            resultSet.getBoolean("sources_pollution_air_exter"),
                            resultSet.getBoolean("moyens_air_sain_inter"),
                            resultSet.getBoolean("moyens_air_sain_exter"),
                            resultSet.getBoolean("actions_publiques_ameliorer_qualite_air"),
                            resultSet.getString("saison_pollue"),
                            resultSet.getString("impact_sante"),
                            resultSet.getString("impact_air_pollue_organe"),
                            resultSet.getBoolean("aeration_logement"),
                            resultSet.getString("frequence_aeration_logement"),
                            resultSet.getString("eviter_trafic_velo"),
                            resultSet.getBoolean("sport"),
                            resultSet.getBoolean("sport_route_trafic"),
                            resultSet.getString("remarques"),
                            resultSet.getTimestamp("date_creation")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Liste_form_partie_3;
    }

}
