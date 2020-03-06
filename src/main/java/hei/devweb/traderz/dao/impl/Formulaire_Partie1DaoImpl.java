package hei.devweb.traderz.dao.impl;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.Formulaire_Partie1Dao;
import hei.devweb.traderz.entities.Formulaire_Partie1;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Formulaire_Partie1DaoImpl implements Formulaire_Partie1Dao {

    public List<Formulaire_Partie1> GetAllFormPartie1(String date_debut, String date_fin) {

        List<Formulaire_Partie1> Liste_form_partie_1 = new ArrayList<>();

        String query = "SELECT * FROM enquete e \n" +
                "where date(e.date_creation) >= '" + date_debut + "'\n" +
                " and date(e.date_creation) <= '" + date_fin + "'" +
                "  GROUP BY id_user\n";
        System.out.println(query);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Liste_form_partie_1.add(new Formulaire_Partie1(
                            resultSet.getInt("id_info_enquete"),
                            resultSet.getInt("id_user"),
                            resultSet.getInt("sexe"),
                            resultSet.getInt("age"),
                            resultSet.getString("situation"),
                            resultSet.getString("domaine"),
                            resultSet.getString("diplome"),
                            resultSet.getBoolean("parent"),
                            resultSet.getString("commune"),
                            resultSet.getString("mot1"),
                            resultSet.getString("mot2"),
                            resultSet.getString("mot3"),
                            resultSet.getTimestamp("date_creation")
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Liste_form_partie_1;
    }


    public JSONArray GetAllQuestionnairesInfoWithDates(String date_debut, String date_fin){


        JSONArray array = new JSONArray();
        String query = "select \n" +
                "\tp.email,\n" +
                "    e.age,\n" +
                "    e.commune,\n" +
                "    e.date_creation,\n" +
                "    e.diplome,\n" +
                "    e.domaine,\n" +
                "    e.parent,\n" +
                "    e.sexe,\n" +
                "    e.situation,\n" +
                "    e.mot1, e.mot2, e.mot3,\n" +
                "    info.actions_publiques_ameliorer_qualite_air,\n" +
                "    info.aeration_logement,\n" +
                "    info.effet_pollution_air_sante,\n" +
                "    info.eviter_trafic_velo,\n" +
                "    info.frequence_aeration_logement,\n" +
                "    info.impact_air_pollue_organe,\n" +
                "    info.impact_sante,\n" +
                "    info.moyens_air_sain_exter,\n" +
                "    info.moyens_air_sain_inter,\n" +
                "    info.pics_pollution,\n" +
                "    info.pollution,\n" +
                "    info.precautions_pic_pollution,\n" +
                "    info.prev_quotidien_q_air_ext,\n" +
                "    info.q_air_general,\n" +
                "    info.q_air_quartier,\n" +
                "    info.recommandation_proteger_pollution_quotidienne,\n" +
                "    info.remarques,\n" +
                "    info.saison_pollue,\n" +
                "    info.sources_pollution_air_exter,\n" +
                "    info.sources_pollution_air_inter,\n" +
                "    info.sport,\n" +
                "    info.sport_route_trafic,\n" +
                "    ss.air_agreable,\n" +
                "    ss.air_sensation,\n" +
                "    ss.capteur_temp,\n" +
                "    ss.climat_salle,\n" +
                "    ss.dist_fenetre,\n" +
                "    ss.dist_ventilo,\n" +
                "    ss.odeur,\n" +
                "    ss.poussiere,\n" +
                "    ss.q_air_hei,\n" +
                "    ss.q_air_salle,\n" +
                "    ss.symptomes,\n" +
                "    ss.temp_sensation\n" +
                "    \n" +
                "from personne p\n" +
                "inner join enquete e ON (p.id = e.id_user)\n" +
                "inner join info_sensibilisation info ON (p.id = info.id_user AND e.date_creation = info.date_creation)\n" +
                "inner join sensation_salles ss ON (p.id = ss.id_user AND info.date_creation = ss.date_creation)\n" +
                "where date(e.date_creation) >= '" + date_debut + "'\n" +
                " and date(e.date_creation) <= '" + date_fin + "'";


        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = (connection).prepareStatement(query)) {
            System.out.println(statement);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    JSONObject record = new JSONObject();
                    record.put("email", resultSet.getString("email"));
                    record.put("age", resultSet.getString("age"));
                    record.put("commune", resultSet.getString("commune"));
                    record.put("date_creation", resultSet.getString("date_creation"));
                    record.put("diplome", resultSet.getString("diplome"));
                    record.put("domaine", resultSet.getString("domaine"));
                    record.put("parent", resultSet.getString("parent"));
                    record.put("sexe", resultSet.getString("sexe"));
                    record.put("situation", resultSet.getString("situation"));
                    record.put("mot1", resultSet.getString("mot1"));
                    record.put("mot2", resultSet.getString("mot2"));
                    record.put("mot3", resultSet.getString("mot3"));
                    record.put("actions_publiques_ameliorer_qualite_air", resultSet.getString("actions_publiques_ameliorer_qualite_air"));
                    record.put("aeration_logement", resultSet.getString("aeration_logement"));
                    record.put("effet_pollution_air_sante", resultSet.getString("effet_pollution_air_sante"));
                    record.put("eviter_trafic_velo", resultSet.getString("eviter_trafic_velo"));
                    record.put("frequence_aeration_logement", resultSet.getString("frequence_aeration_logement"));
                    record.put("impact_air_pollue_organe", resultSet.getString("impact_air_pollue_organe"));
                    record.put("impact_sante", resultSet.getString("impact_sante"));
                    record.put("moyens_air_sain_exter", resultSet.getString("moyens_air_sain_exter"));
                    record.put("moyens_air_sain_inter", resultSet.getString("moyens_air_sain_inter"));
                    record.put("pics_pollution", resultSet.getString("pics_pollution"));
                    record.put("pollution", resultSet.getString("pollution"));
                    record.put("precautions_pic_pollution", resultSet.getString("precautions_pic_pollution"));
                    record.put("prev_quotidien_q_air_ext", resultSet.getString("prev_quotidien_q_air_ext"));
                    record.put("q_air_general", resultSet.getString("q_air_general"));
                    record.put("q_air_quartier", resultSet.getString("q_air_quartier"));
                    record.put("recommandation_proteger_pollution_quotidienne", resultSet.getString("recommandation_proteger_pollution_quotidienne"));
                    record.put("remarques", resultSet.getString("remarques"));
                    record.put("saison_pollue", resultSet.getString("saison_pollue"));
                    record.put("sources_pollution_air_exter", resultSet.getString("sources_pollution_air_exter"));
                    record.put("sources_pollution_air_inter", resultSet.getString("sources_pollution_air_inter"));
                    record.put("sport", resultSet.getString("sport"));
                    record.put("sport_route_trafic", resultSet.getString("sport_route_trafic"));
                    record.put("air_agreable", resultSet.getString("air_agreable"));
                    record.put("air_sensation", resultSet.getString("air_sensation"));
                    record.put("capteur_temp", resultSet.getString("capteur_temp"));
                    record.put("climat_salle", resultSet.getString("climat_salle"));
                    record.put("dist_fenetre", resultSet.getString("dist_fenetre"));
                    record.put("dist_ventilo", resultSet.getString("dist_ventilo"));
                    record.put("odeur", resultSet.getString("odeur"));
                    record.put("poussiere", resultSet.getString("poussiere"));
                    record.put("q_air_hei", resultSet.getString("q_air_hei"));
                    record.put("q_air_salle", resultSet.getString("q_air_salle"));
                    record.put("symptomes", resultSet.getString("symptomes"));
                    record.put("temp_sensation", resultSet.getString("temp_sensation"));

                    array.add(record);
                } }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(array);
        return  array;
    }



}
