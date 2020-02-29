package hei.devweb.traderz.entities;


import java.sql.Timestamp;

public class Formulaire_Partie3 {

    private Integer id_info_sensibilisation;
    private Integer id_user;
    private String qualite_air_quartier;
    private Boolean pollution;
    private Boolean qualite_air_general;
    private Boolean prev_quotidien_q_air_ext;
    private Boolean effet_pollution_air_sante;
    private Boolean recommandation_proteger_pollution_quotidienne;
    private Boolean pics_pollution;
    private Boolean precautions_pic_pollution;
    private Boolean sources_pollution_air_inter;
    private Boolean sources_pollution_air_exter;
    private Boolean moyens_air_sain_inter;
    private Boolean moyens_air_sain_exter;
    private Boolean actions_publiques_ameliorer_qualite_air;
    private String saison_pollue;
    private String impact_sante;
    private String impact_air_pollue_organe;
    private Boolean aeration_logement;
    private String frequence_aeration_logement;
    private String eviter_trafic_velo;
    private Boolean sport;
    private Boolean sport_route_trafic;
    private String remarques;
    private Timestamp date_creation;

    public Formulaire_Partie3(Integer id_info_sensibilisation, Integer id_user, String qualite_air_quartier, Boolean pollution, Boolean qualite_air_general, Boolean prev_quotidien_q_air_ext, Boolean effet_pollution_air_sante, Boolean recommandation_proteger_pollution_quotidienne, Boolean pics_pollution, Boolean precautions_pic_pollution, Boolean sources_pollution_air_inter, Boolean sources_pollution_air_exter, Boolean moyens_air_sain_inter, Boolean moyens_air_sain_exter, Boolean actions_publiques_ameliorer_qualite_air, String saison_pollue, String impact_sante, String impact_air_pollue_organe, Boolean aeration_logement, String frequence_aeration_logement, String eviter_trafic_velo, Boolean sport, Boolean sport_route_trafic, String remarques, Timestamp date_creation) {
        this.id_info_sensibilisation = id_info_sensibilisation;
        this.id_user = id_user;
        this.qualite_air_quartier = qualite_air_quartier;
        this.pollution = pollution;
        this.qualite_air_general = qualite_air_general;
        this.prev_quotidien_q_air_ext = prev_quotidien_q_air_ext;
        this.effet_pollution_air_sante = effet_pollution_air_sante;
        this.recommandation_proteger_pollution_quotidienne = recommandation_proteger_pollution_quotidienne;
        this.pics_pollution = pics_pollution;
        this.precautions_pic_pollution = precautions_pic_pollution;
        this.sources_pollution_air_inter = sources_pollution_air_inter;
        this.sources_pollution_air_exter = sources_pollution_air_exter;
        this.moyens_air_sain_inter = moyens_air_sain_inter;
        this.moyens_air_sain_exter = moyens_air_sain_exter;
        this.actions_publiques_ameliorer_qualite_air = actions_publiques_ameliorer_qualite_air;
        this.saison_pollue = saison_pollue;
        this.impact_sante = impact_sante;
        this.impact_air_pollue_organe = impact_air_pollue_organe;
        this.aeration_logement = aeration_logement;
        this.frequence_aeration_logement = frequence_aeration_logement;
        this.eviter_trafic_velo = eviter_trafic_velo;
        this.sport = sport;
        this.sport_route_trafic = sport_route_trafic;
        this.remarques = remarques;
        this.date_creation = date_creation;
    }


    public Integer getId_info_sensibilisation() {
        return id_info_sensibilisation;
    }

    public void setId_info_sensibilisation(Integer id_info_sensibilisation) {
        this.id_info_sensibilisation = id_info_sensibilisation;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getQualite_air_quartier() {
        return qualite_air_quartier;
    }

    public void setQualite_air_quartier(String qualite_air_quartier) {
        this.qualite_air_quartier = qualite_air_quartier;
    }

    public Boolean getPollution() {
        return pollution;
    }

    public void setPollution(Boolean pollution) {
        this.pollution = pollution;
    }

    public Boolean getQualite_air_general() {
        return qualite_air_general;
    }

    public void setQualite_air_general(Boolean qualite_air_general) {
        this.qualite_air_general = qualite_air_general;
    }

    public Boolean getPrev_quotidien_q_air_ext() {
        return prev_quotidien_q_air_ext;
    }

    public void setPrev_quotidien_q_air_ext(Boolean prev_quotidien_q_air_ext) {
        this.prev_quotidien_q_air_ext = prev_quotidien_q_air_ext;
    }

    public Boolean getEffet_pollution_air_sante() {
        return effet_pollution_air_sante;
    }

    public void setEffet_pollution_air_sante(Boolean effet_pollution_air_sante) {
        this.effet_pollution_air_sante = effet_pollution_air_sante;
    }

    public Boolean getRecommandation_proteger_pollution_quotidienne() {
        return recommandation_proteger_pollution_quotidienne;
    }

    public void setRecommandation_proteger_pollution_quotidienne(Boolean recommandation_proteger_pollution_quotidienne) {
        this.recommandation_proteger_pollution_quotidienne = recommandation_proteger_pollution_quotidienne;
    }

    public Boolean getPics_pollution() {
        return pics_pollution;
    }

    public void setPics_pollution(Boolean pics_pollution) {
        this.pics_pollution = pics_pollution;
    }

    public Boolean getPrecautions_pic_pollution() {
        return precautions_pic_pollution;
    }

    public void setPrecautions_pic_pollution(Boolean precautions_pic_pollution) {
        this.precautions_pic_pollution = precautions_pic_pollution;
    }

    public Boolean getSources_pollution_air_inter() {
        return sources_pollution_air_inter;
    }

    public void setSources_pollution_air_inter(Boolean sources_pollution_air_inter) {
        this.sources_pollution_air_inter = sources_pollution_air_inter;
    }

    public Boolean getSources_pollution_air_exter() {
        return sources_pollution_air_exter;
    }

    public void setSources_pollution_air_exter(Boolean sources_pollution_air_exter) {
        this.sources_pollution_air_exter = sources_pollution_air_exter;
    }

    public Boolean getMoyens_air_sain_inter() {
        return moyens_air_sain_inter;
    }

    public void setMoyens_air_sain_inter(Boolean moyens_air_sain_inter) {
        this.moyens_air_sain_inter = moyens_air_sain_inter;
    }

    public Boolean getMoyens_air_sain_exter() {
        return moyens_air_sain_exter;
    }

    public void setMoyens_air_sain_exter(Boolean moyens_air_sain_exter) {
        this.moyens_air_sain_exter = moyens_air_sain_exter;
    }

    public Boolean getActions_publiques_ameliorer_qualite_air() {
        return actions_publiques_ameliorer_qualite_air;
    }

    public void setActions_publiques_ameliorer_qualite_air(Boolean actions_publiques_ameliorer_qualite_air) {
        this.actions_publiques_ameliorer_qualite_air = actions_publiques_ameliorer_qualite_air;
    }

    public String getSaison_pollue() {
        return saison_pollue;
    }

    public void setSaison_pollue(String saison_pollue) {
        this.saison_pollue = saison_pollue;
    }

    public String getImpact_sante() {
        return impact_sante;
    }

    public void setImpact_sante(String impact_sante) {
        this.impact_sante = impact_sante;
    }

    public String getImpact_air_pollue_organe() {
        return impact_air_pollue_organe;
    }

    public void setImpact_air_pollue_organe(String impact_air_pollue_organe) {
        this.impact_air_pollue_organe = impact_air_pollue_organe;
    }

    public Boolean getAeration_logement() {
        return aeration_logement;
    }

    public void setAeration_logement(Boolean aeration_logement) {
        this.aeration_logement = aeration_logement;
    }

    public String getFrequence_aeration_logement() {
        return frequence_aeration_logement;
    }

    public void setFrequence_aeration_logement(String frequence_aeration_logement) {
        this.frequence_aeration_logement = frequence_aeration_logement;
    }

    public String getEviter_trafic_velo() {
        return eviter_trafic_velo;
    }

    public void setEviter_trafic_velo(String eviter_trafic_velo) {
        this.eviter_trafic_velo = eviter_trafic_velo;
    }

    public Boolean getSport() {
        return sport;
    }

    public void setSport(Boolean sport) {
        this.sport = sport;
    }

    public Boolean getSport_route_trafic() {
        return sport_route_trafic;
    }

    public void setSport_route_trafic(Boolean sport_route_trafic) {
        this.sport_route_trafic = sport_route_trafic;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }
}
