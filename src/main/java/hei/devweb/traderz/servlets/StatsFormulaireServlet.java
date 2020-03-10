package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Formulaire_Partie1;
import hei.devweb.traderz.entities.Formulaire_Partie2;
import hei.devweb.traderz.entities.Formulaire_Partie3;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.Formulaire_Partie1_Manager;
import hei.devweb.traderz.managers.Formulaire_Partie2_Manager;
import hei.devweb.traderz.managers.Formulaire_Partie3_Manager;
import hei.devweb.traderz.managers.UserManager;
import org.json.simple.JSONArray;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/Admin/Stats_Formulaire"})
public class StatsFormulaireServlet extends PrivateServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session



//   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     PARTIE GESTION DU CSV    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        Date date = new Date();
        String today= new SimpleDateFormat("yyyy-MM-dd").format(date);

        String debut_date = ((debut_date = req.getParameter("debut_date")) != null) ? debut_date : "2020-01-01";
        String fin_date = ((fin_date = req.getParameter("fin_date")) != null) ? fin_date : today;

        JSONArray array_of_all_questionnaire_info = Formulaire_Partie1_Manager.getInstance().GetAllQuestionnairesInfoWithDates(debut_date, fin_date);

        context.setVariable("debut_date", debut_date);
        context.setVariable("fin_date", fin_date);
        context.setVariable("array_of_all_questionnaire_info", array_of_all_questionnaire_info);




//   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     PARTIE 1 QUESTIONNAIRE     <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        List<Formulaire_Partie1> liste_form_partie_1 = Formulaire_Partie1_Manager.getInstance().GetAllFormPartie1(debut_date, fin_date);

//        Q1
        Integer nbr_homme = 0;
        Integer nbr_femme = 0;
//        Q2
        Integer somme_all_age = 0;
        Integer nbr_age_inf_18 = 0;
        Integer nbr_age_sup_26 = 0;
//        Q3
        Integer nbr_etudiant = 0;
        Integer nbr_enseignant = 0;
        Integer nbr_personnel = 0;
        Integer nbr_autre_q3 = 0;
//        Q4
        Integer nbr_BFA = 0;
        Integer nbr_BAA = 0;
        Integer nbr_BTP = 0;
        Integer nbr_CITE = 0;
        Integer nbr_CCM = 0;
        Integer nbr_ENT = 0;
        Integer nbr_ESEA = 0;
        Integer nbr_IMS = 0;
        Integer nbr_ITI = 0;
        Integer nbr_MOIL = 0;
        Integer nbr_MR = 0;
        Integer nbr_SC = 0;
        Integer nbr_TIMTEX = 0;
        Integer nbr_autre_q4 = 0;
//        Q5
        Integer nbr_pas_diplome = 0;
        Integer nbr_BAC_2 = 0;
        Integer nbr_CAP = 0;
        Integer nbr_BAC_5 = 0;
        Integer nbr_BAC = 0;
        Integer nbr_autre_q5 = 0;
        Integer nbr_BAC_3 = 0;
//        Q6
        Integer nbr_parent = 0;

        for(Formulaire_Partie1 partie1 : liste_form_partie_1){
//    PARITE 1
//            Question 1
            if(partie1.getSexe()==1){
                nbr_homme += 1;
            }else{
                nbr_femme += 1;
            }
//            Question 2
            if(partie1.getAge()<18){
                nbr_age_inf_18 += 1;
            }else if(partie1.getAge()>26){
                nbr_age_sup_26 += 1;
            }
            somme_all_age += partie1.getAge();
//            Question 3
            switch (partie1.getSituation()) {
                case "etudiant":
                    nbr_etudiant += 1;
                    break;
                case "enseignant":
                    nbr_enseignant += 1;
                    break;
                case "personnel":
                    nbr_personnel += 1;
                    break;
                default:
                    nbr_autre_q3 += 1;
                    break;
            }
//            Question 4
            switch (partie1.getDomaine()) {
                case "BFA":
                    nbr_BFA += 1;
                    break;
                case "BAA":
                    nbr_BAA += 1;
                    break;
                case "BTP":
                    nbr_BTP += 1;
                    break;
                case "CITE":
                    nbr_CITE += 1;
                    break;
                case "CCM":
                    nbr_CCM += 1;
                    break;
                case "ENT":
                    nbr_ENT += 1;
                    break;
                case "ESEA":
                    nbr_ESEA += 1;
                    break;
                case "IMS":
                    nbr_IMS += 1;
                    break;
                case "ITI":
                    nbr_ITI += 1;
                    break;
                case "MOIL":
                    nbr_MOIL += 1;
                    break;
                case "MR":
                    nbr_MR += 1;
                    break;
                case "SC":
                    nbr_SC += 1;
                    break;
                case "TIMTEX":
                    nbr_TIMTEX += 1;
                    break;
                default:
                    String domaine = (partie1.getDomaine() != null ? partie1.getDomaine() : "");
                    if (!domaine.equals("")) {
                        nbr_autre_q4 += 1;
                    }
                    break;
            }

//            Question 5
            if(partie1.getDiplome().equals("Bac")){
                nbr_BAC += 1;
            }else if(partie1.getDiplome().equals("Pas_de_diplôme")){
                nbr_pas_diplome += 1;
            }else if(partie1.getDiplome().equals("BEP")){
                nbr_CAP += 1;
            }else if(partie1.getDiplome().equals("Bac_2")){
                nbr_BAC_2 += 1;
            }else if(partie1.getDiplome().equals("Bac_5")){
                nbr_BAC_5 += 1;
            }else if(partie1.getDiplome().equals("Bac_3")){
                nbr_BAC_3 += 1;
            }else {
                nbr_autre_q5 += 1;
            }
//            Question 6
            if(partie1.getParent()){
                nbr_parent += 1;
            }
//            Question 7
//Faire une requete spécialisée pour avoir le top des villes

//            Question 8
//            Faire une requete spécialisée pour avoir les mots les plus utilisés

        }


//        Set Attributes
//        Q° 1
        context.setVariable("nbr_homme", nbr_homme);
        context.setVariable("nbr_femme", nbr_femme);
//        Q° 2
        context.setVariable("somme_all_age", somme_all_age);
        context.setVariable("nbr_age_inf_18", nbr_age_inf_18);
        context.setVariable("nbr_age_sup_26", nbr_age_sup_26);
//        Q° 3
        context.setVariable("nbr_etudiant", nbr_etudiant);
        context.setVariable("nbr_enseignant",nbr_enseignant);
        context.setVariable("nbr_personnel",nbr_personnel);
        context.setVariable("nbr_autre_q3", nbr_autre_q3);
//        Q° 4
        context.setVariable("nbr_BAA", nbr_BAA);
        context.setVariable("nbr_BFA", nbr_BFA);
        context.setVariable("nbr_BTP", nbr_BTP);
        context.setVariable("nbr_CCM", nbr_CCM);
        context.setVariable("nbr_CITE", nbr_CITE);
        context.setVariable("nbr_ENT", nbr_ENT);
        context.setVariable("nbr_ESEA", nbr_ESEA);
        context.setVariable("nbr_IMS", nbr_IMS);
        context.setVariable("nbr_ITI", nbr_ITI);
        context.setVariable("nbr_MOIL", nbr_MOIL);
        context.setVariable("nbr_MR", nbr_MR);
        context.setVariable("nbr_SC", nbr_SC);
        context.setVariable("nbr_TIMTEX", nbr_TIMTEX);
        context.setVariable("nbr_autre_q4", nbr_autre_q4);
//        Q° 5
        context.setVariable("nbr_BAC", nbr_BAC);
        context.setVariable("nbr_BAC_2", nbr_BAC_2);
        context.setVariable("nbr_BAC_3", nbr_BAC_3);
        context.setVariable("nbr_BAC_5", nbr_BAC_5);
        context.setVariable("nbr_pas_diplome", nbr_pas_diplome);
        context.setVariable("nbr_CAP", nbr_CAP);
        context.setVariable("nbr_autre_q5", nbr_autre_q5);
//        Q° 6
        context.setVariable("nbr_parent", nbr_parent);



//   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     PARTIE 2 QUESTIONNAIRE     <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    List<Formulaire_Partie2> liste_form_partie2 = Formulaire_Partie2_Manager.getInstance().GetAllFormPartie2(debut_date, fin_date);

//    Q°9
    Integer nbr_q_air_hei_bonne = 0;
    Integer nbr_q_air_hei_p_bonne = 0;
    Integer nbr_q_air_hei_p_mauvaise = 0;
    Integer nbr_q_air_hei_mauvaise = 0;
//    Q°11
    Integer dist_fenetre_1m = 0;
    Integer dist_fenetre_1_3m = 0;
    Integer dist_fenetre_3_5m = 0;
    Integer dist_fenetre_5m = 0;
//    Q°12
    Integer dist_ventilo_2m = 0;
    Integer dist_ventilo_2_5m = 0;
    Integer dist_ventilo_plus5m = 0;
    Integer dist_ventilo_jsp = 0;
//    Q°13
    Integer chaud_moins = 0;
    Integer chaud = 0;
    Integer chaud_plus = 0;
    Integer froid_moins = 0;
    Integer froid = 0;
    Integer froid_plus = 0;
//    Q°14
    Integer temp_assez_confortable = 0;
    Integer temp_confortable = 0;
    Integer temp_assez_inconfortable = 0;
    Integer temp_inconfortable = 0;
//    Q°15
    Double temp_min = +100000.;
    Double temp_max = -100000.;
    Double somme_temp = 0.;
    Integer count_temp = 0;
//    Q°16
    Integer air_sec = 0;
    Integer air_tres_sec = 0;
    Integer air_humide = 0;
    Integer air_tres_humide = 0;
//    Q°17
    Integer hum_assez_agreable = 0;
    Integer hum_agreable = 0;
    Integer hum_assez_desagreable = 0;
    Integer hum_desagreable = 0;
//    Q°18
    Integer odeur_nourriture = 0;
    Integer odeur_corporelle = 0;
    Integer odeur_moisi = 0;
    Integer odeur_humidite = 0;
    Integer odeur_poussiere = 0;
    Integer odeur_parfum = 0;
    Integer odeur_nettoyage = 0;
    Integer odeur_tabac = 0;
    Integer aucune_odeur = 0;
    Integer nbr_autre_q18 = 0;
//    Q°19
    Integer yeux_rouge = 0;
    Integer yeux_secs = 0;
    Integer nez_bouche = 0;
    Integer rougeurs = 0;
    Integer grattement = 0;
    Integer yeux_coulent = 0;
    Integer saignement_nez = 0;
    Integer gonflement = 0;
    Integer secheresse = 0;
    Integer maux_tete = 0;
    Integer difficultes_concentration = 0;
    Integer sensation_fatigue = 0;
    Integer aucun_symptome = 0;
    Integer nbr_autre_q19 = 0;
//    Q°20
    Integer poussiere = 0;
    Integer pas_poussiere = 0;
//    Q°21
    Integer aussi_bonne = 0;
    Integer aussi_mauvaise = 0;
    Integer moins_bonne = 0;
    Integer Meilleure = 0;
    Integer aucune_reference = 0;




    for(Formulaire_Partie2 partie2 : liste_form_partie2){
//        Question 9
        if(partie2.getQualite_air_hei().equals("bonne+")){
            nbr_q_air_hei_bonne += 1;
        }else if(partie2.getQualite_air_hei().equals("bonne")){
            nbr_q_air_hei_p_bonne += 1;
        }else if(partie2.getQualite_air_hei().equals("mauvaise")){
            nbr_q_air_hei_p_mauvaise += 1;
        }else if(partie2.getQualite_air_hei().equals("mauvaise-")){
            nbr_q_air_hei_mauvaise += 1;
        }
//        Question 10
//        Requete spécialisée pour avoir la liste des salles les plus fréquentées

//        Question 11
        if(partie2.getDistance_fenetre().equals("1m")){
            dist_fenetre_1m += 1;
        }else if(partie2.getDistance_fenetre().equals("3m")){
            dist_fenetre_1_3m += 1;
        }else if(partie2.getDistance_fenetre().equals("5m")){
            dist_fenetre_3_5m += 1;
        }else if(partie2.getDistance_fenetre().equals("plus5m")){
            dist_fenetre_5m += 1;
        }

//        Question 12
        if(partie2.getDistance_ventilo().equals("2m")){
            dist_ventilo_2m += 1;
        }else if(partie2.getDistance_ventilo().equals("5m")){
            dist_ventilo_2_5m += 1;
        }else if(partie2.getDistance_ventilo().equals("plus5m")){
            dist_ventilo_plus5m += 1;
        }else if(partie2.getDistance_ventilo().equals("jsp")){
            dist_ventilo_jsp += 1;
        }
//      Question 13
        if(partie2.getClimat_salle().equals("chaud-")){
            chaud_moins += 1;
        }else if(partie2.getClimat_salle().equals("chaud")){
            chaud += 1;
        }else if(partie2.getClimat_salle().equals("chaud+")){
            chaud_plus += 1;
        }else if(partie2.getClimat_salle().equals("froid-")){
            froid_moins += 1;
        }else if(partie2.getClimat_salle().equals("froid")){
            froid += 1;
        }else if(partie2.getClimat_salle().equals("froid+")){
            froid_plus += 1;
        }
//        Question 14
        if(partie2.getTemp_sensation().equals("confortable")){
            temp_assez_confortable += 1;
        }else if(partie2.getTemp_sensation().equals("confortable+")){
            temp_confortable += 1;
        }else if(partie2.getTemp_sensation().equals("inconfortable+")){
            temp_inconfortable += 1;
        }else if(partie2.getTemp_sensation().equals("inconfortable")){
            temp_assez_inconfortable += 1;
        }
//        Question 15
        if(partie2.getCapteur_temp()>temp_max){
            temp_max = partie2.getCapteur_temp();
        }
        if(partie2.getCapteur_temp()<temp_min){
            temp_min = partie2.getCapteur_temp();
        }
        count_temp += 1;
        somme_temp += partie2.getCapteur_temp();
//        Question 16
        if(partie2.getAir_sensation().equals("Sec")){
            air_sec += 1;
        }else if(partie2.getAir_sensation().equals("Sec+")){
            air_tres_sec += 1;
        }else if(partie2.getAir_sensation().equals("Humide+")){
            air_tres_humide += 1;
        }else if(partie2.getAir_sensation().equals("Humide")){
            air_humide += 1;
        }
//        Question 17
        if(partie2.getAir_agreable().equals("agréable")){
            hum_assez_agreable += 1;
        }else if(partie2.getAir_agreable().equals("agréable+")){
            hum_agreable += 1;
        }else if(partie2.getAir_agreable().equals("désagréable+")){
            hum_desagreable += 1;
        }else if(partie2.getAir_agreable().equals("désagréable")){
            hum_assez_desagreable += 1;
        }
//        Question 18
        String liste_of_odeur_string = partie2.getOdeur() != null ? partie2.getOdeur() : "";
        if(!liste_of_odeur_string.equals("")){
            List<String> liste_of_odeur = Arrays.asList(liste_of_odeur_string.split("\\s*,\\s*"));
            for(String odeur : liste_of_odeur){
                if(odeur.equals("nourriture")){
                    odeur_nourriture += 1;
                }else if(odeur.equals("moisi")){
                    odeur_moisi += 1;
                }else if(odeur.equals("humidité")){
                    odeur_humidite += 1;
                }else if(odeur.equals("poussière")){
                    odeur_poussiere += 1;
                }else if(odeur.equals("parfum")){
                    odeur_parfum += 1;
                }else if(odeur.equals("produit")){
                    odeur_nettoyage += 1;
                }else if(odeur.equals("tabac")){
                    odeur_tabac += 1;
                }else if(odeur.equals("aucune")){
                    aucune_odeur += 1;
                }else{
                    nbr_autre_q18 += 1;
                }
            }
        }
//        Question 19
        String liste_of_symptome_string = partie2.getSymptome() != null ? partie2.getSymptome() : "";
        if(!liste_of_symptome_string.equals("")){
            List<String> liste_of_symptome = Arrays.asList(liste_of_symptome_string.split("\\s*,\\s*"));
            for(String symptome : liste_of_symptome){
                if(symptome.equals("yeux_irrités")){
                    yeux_rouge += 1;
                }else if(symptome.equals("Yeux_secs")){
                    yeux_secs += 1;
                }else if(symptome.equals("Nez_bouché")){
                    nez_bouche += 1;
                }else if(symptome.equals("irritations_peau")){
                    rougeurs += 1;
                }else if(symptome.equals("Grattement_cuir_chevelu")){
                    grattement += 1;
                }else if(symptome.equals("yeux_qui_coulent")){
                    yeux_coulent += 1;
                }else if(symptome.equals("Saignements_nez")){
                    saignement_nez += 1;
                }else if(symptome.equals("Gonflement")){
                    gonflement += 1;
                }else if(symptome.equals("irritation_voies_respiratoires")){
                    secheresse += 1;
                }else if(symptome.equals("Maux_tête")){
                    maux_tete += 1;
                }else if(symptome.equals("Difficultés_concentration")){
                    difficultes_concentration += 1;
                }else if(symptome.equals("Sensation_fatigue")){
                    sensation_fatigue += 1;
                }else if(symptome.equals("aucun")){
                    aucun_symptome += 1;
                }else{
                    nbr_autre_q19 += 1;
                }
            }
        }
//        Question 20
        if(partie2.getPoussiere()){
            poussiere += 1;
        }else{
            pas_poussiere += 1;
        }
//        Question 21
        if(partie2.getQualite_air_salle().equals("aussi_bonne")){
            aussi_bonne += 1;
        }else if(partie2.getQualite_air_salle().equals("Meilleure")){
            Meilleure += 1;
        }else if(partie2.getQualite_air_salle().equals("aussi_mauvaise")){
            aussi_mauvaise += 1;
        }else if(partie2.getQualite_air_salle().equals("moins_bonne")){
            moins_bonne += 1;
        }else if(partie2.getQualite_air_salle().equals("aucune_référence")){
            aucune_reference += 1;
        }





    }


    //    Q° 9
        context.setVariable("nbr_q_air_hei_bonne", nbr_q_air_hei_bonne);
        context.setVariable("nbr_q_air_hei_mauvaise", nbr_q_air_hei_mauvaise);
        context.setVariable("nbr_q_air_hei_p_mauvaise", nbr_q_air_hei_p_mauvaise);
        context.setVariable("nbr_q_air_hei_p_bonne", nbr_q_air_hei_p_bonne);

    //    Q° 11
        context.setVariable("dist_fenetre_1m", dist_fenetre_1m);
        context.setVariable("dist_fenetre_1_3m", dist_fenetre_1_3m);
        context.setVariable("dist_fenetre_3_5m", dist_fenetre_3_5m);
        context.setVariable("dist_fenetre_5m", dist_fenetre_5m);
    //    Q° 12
        context.setVariable("dist_ventilo_2m", dist_ventilo_2m);
        context.setVariable("dist_ventilo_2_5m", dist_ventilo_2_5m);
        context.setVariable("dist_ventilo_plus5m", dist_ventilo_plus5m);
        context.setVariable("dist_ventilo_jsp", dist_ventilo_jsp);
    //    Q°13
        context.setVariable("chaud_plus", chaud_plus);
        context.setVariable("chaud", chaud);
        context.setVariable("chaud_moins", chaud_moins);
        context.setVariable("froid_moins", froid_moins);
        context.setVariable("froid", froid);
        context.setVariable("froid_plus", froid_plus);
//        Q°14
        context.setVariable("temp_assez_confortable", temp_assez_confortable);
        context.setVariable("temp_assez_inconfortable", temp_assez_inconfortable);
        context.setVariable("temp_confortable", temp_confortable);
        context.setVariable("temp_inconfortable", temp_inconfortable);
//        Q°15
        context.setVariable("temp_min", temp_min);
        context.setVariable("temp_max", temp_max);
        context.setVariable("somme_temp", somme_temp);
        context.setVariable("count_temp", count_temp);
//        Q°16
        context.setVariable("air_humide", air_humide);
        context.setVariable("air_sec", air_sec);
        context.setVariable("air_tres_humide", air_tres_humide);
        context.setVariable("air_tres_sec", air_tres_sec);
//        Q°17
        context.setVariable("hum_agreable", hum_agreable);
        context.setVariable("hum_assez_agreable", hum_assez_agreable);
        context.setVariable("hum_desagreable", hum_desagreable);
        context.setVariable("hum_assez_desagreable", hum_assez_desagreable);
//        Q°18
        context.setVariable("odeur_nourriture", odeur_nourriture);
        context.setVariable("odeur_corporelle", odeur_corporelle);
        context.setVariable("odeur_moisi", odeur_moisi);
        context.setVariable("odeur_humidite", odeur_humidite);
        context.setVariable("odeur_poussiere", odeur_poussiere);
        context.setVariable("odeur_parfum", odeur_parfum);
        context.setVariable("odeur_nettoyage", odeur_nettoyage);
        context.setVariable("odeur_tabac", odeur_tabac);
        context.setVariable("aucune_odeur", aucune_odeur);
        context.setVariable("nbr_autre_q18", nbr_autre_q18);
//        Q°19
        context.setVariable("yeux_rouge", yeux_rouge);
        context.setVariable("yeux_secs", yeux_secs);
        context.setVariable("nez_bouche", nez_bouche);
        context.setVariable("rougeurs", rougeurs);
        context.setVariable("grattement", grattement);
        context.setVariable("yeux_coulent", yeux_coulent);
        context.setVariable("saignement_nez", saignement_nez);
        context.setVariable("gonflement", gonflement);
        context.setVariable("secheresse", secheresse);
        context.setVariable("maux_tete", maux_tete);
        context.setVariable("difficultes_concentration", difficultes_concentration);
        context.setVariable("sensation_fatigue", sensation_fatigue);
        context.setVariable("aucun_symptome", aucun_symptome);
        context.setVariable("nbr_autre_q19", nbr_autre_q19);
//        Q°20
        context.setVariable("poussiere", poussiere);
        context.setVariable("pas_poussiere", pas_poussiere);
//        Q°21
        context.setVariable("aussi_bonne", aussi_bonne);
        context.setVariable("moins_bonne", moins_bonne);
        context.setVariable("Meilleure", Meilleure);
        context.setVariable("aucune_reference", aucune_reference);
        context.setVariable("aussi_mauvaise", aussi_mauvaise);


        //   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     PARTIE 2 QUESTIONNAIRE     <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        List<Formulaire_Partie3> liste_form_partie3 = Formulaire_Partie3_Manager.getInstance().GetAllFormPartie3(debut_date, fin_date);

        Integer reponse_totale = 0;
//        Q°22
        Integer nbr_q_air_quartier_bonne = 0;
        Integer nbr_q_air_quartier_p_bonne = 0;
        Integer nbr_q_air_quartier_p_mauvaise = 0;
        Integer nbr_q_air_quartier_mauvaise = 0;
        Integer nbr_q_air_quartier_jsp = 0;
//        Q°23
        Integer connais_pollution_ambiante = 0;
//        Q°24
//        1)
        Integer qualite_air_general = 0;
//        2)
        Integer prev_quotidienne_q_air_ext = 0;
//        3)
        Integer effet_pollution_sante = 0;
//        4)
        Integer recommandation_protection_pollution = 0;
//        5)
        Integer pics_pollution = 0;
//        6)
        Integer precaution_pics_pollution = 0;
//        7)
        Integer source_pollution_int = 0;
//        8)
        Integer source_pollution_ext = 0;
//        9)
        Integer moyens_air_sain_int = 0;
//        10)
        Integer moyens_air_sain_ext = 0;
//        11)
        Integer actions_publiques = 0;
//        Q°25
        Integer printemps = 0;
        Integer ete = 0;
        Integer automne = 0;
        Integer hiver = 0;
//        Q°26
        Integer fort_impact_sante = 0;
        Integer faible_impact_sante = 0;
        Integer pas_impact_sante = 0;
//        Q°27
        Integer aerer_logement = 0;
//        Q°28
        Integer frequence_aeration_jour = 0;
        Integer frequence_aeration_1_semaine = 0;
        Integer frequence_aeration_4_semaine = 0;
        Integer frequence_aeration_mois = 0;
//        Q°29
        Integer evite_pollution_souvent = 0;
        Integer evite_pollution_parfois = 0;
        Integer evite_pollution_jamais = 0;
        Integer evite_pollution_pas_concerne = 0;
//        Q°30
        Integer pratique_course_a_pied = 0;
//        Q°31
        Integer evite_traffic_course_a_pied = 0;
//        Q°32
        Integer nbr_remarque = 0;
        for(Formulaire_Partie3 partie3 : liste_form_partie3){
            reponse_totale += 1;
//            Question 22
            if(partie3.getQualite_air_quartier().equals("bonne+")){
                nbr_q_air_hei_bonne += 1;
            }else if(partie3.getQualite_air_quartier().equals("bonne")){
                nbr_q_air_hei_p_bonne += 1;
            }else if(partie3.getQualite_air_quartier().equals("mauvaise")){
                nbr_q_air_hei_p_mauvaise += 1;
            }else if(partie3.getQualite_air_quartier().equals("mauvaise-")){
                nbr_q_air_hei_mauvaise += 1;
            }else if(partie3.getQualite_air_quartier().equals("jsp")){
                nbr_q_air_hei_mauvaise += 1;
            }
//            Question 23
            if(partie3.getPollution()){
                connais_pollution_ambiante += 1;
            }
//            Question 24
//            1)
            if(partie3.getQualite_air_general()){
                qualite_air_general += 1;
            }
//            2)
            if(partie3.getPrev_quotidien_q_air_ext()){
                prev_quotidienne_q_air_ext += 1;
            }
//            3)
            if (partie3.getEffet_pollution_air_sante()){
                effet_pollution_sante += 1;
            }
//            4)
            if(partie3.getRecommandation_proteger_pollution_quotidienne()){
                recommandation_protection_pollution += 1;
            }
//            5)
            if(partie3.getPics_pollution()){
                pics_pollution += 1;
            }
//            6)
            if(partie3.getPrecautions_pic_pollution()){
                precaution_pics_pollution += 1;
            }
//            7)
            if(partie3.getSources_pollution_air_inter()){
                source_pollution_int += 1;
            }
//            8)
            if(partie3.getSources_pollution_air_exter()){
                source_pollution_ext += 1;
            }
//            9)
            if(partie3.getMoyens_air_sain_inter()){
                moyens_air_sain_int += 1;
            }
//            10)
            if(partie3.getMoyens_air_sain_exter()){
                moyens_air_sain_ext += 1;
            }
//            11)
            if(partie3.getActions_publiques_ameliorer_qualite_air()){
                actions_publiques += 1;
            }
//            Question 25

            String liste_of_saison_string = partie3.getSaison_pollue() != null ? partie3.getSaison_pollue() : "";
            if(!liste_of_saison_string.equals("")){
                List<String> liste_of_saison = Arrays.asList(liste_of_saison_string.split("\\s*,\\s*"));
                for(String saison : liste_of_saison){
                    if(saison.equals("printemps")){
                        printemps += 1;
                    }else if(saison.equals("Ete")){
                        ete += 1;
                    }else if(saison.equals("Automne")){
                        automne += 1;
                    }else if(saison.equals("Hiver")){
                        hiver += 1;
                    }
                }
            }
//            Question 26
            if(partie3.getImpact_sante().equals("oui+")){
                fort_impact_sante += 1;
            }else if(partie3.getImpact_sante().equals("Non")){
                pas_impact_sante += 1;
            }else{
                faible_impact_sante += 1;
            }
//            Question 27
            if(partie3.getAeration_logement()){
                aerer_logement += 1;
            }
//            Question 28
            if(partie3.getFrequence_aeration_logement().equals("une_à_2_fois_par_jour")){
                frequence_aeration_jour += 1;
            }else if(partie3.getFrequence_aeration_logement().equals("deux_à_4_fois_par_semaine")){
                frequence_aeration_4_semaine += 1;
            }else if(partie3.getFrequence_aeration_logement().equals("une_fois_par_semaine")){
                frequence_aeration_1_semaine += 1;
            }else if(partie3.getFrequence_aeration_logement().equals("une_fois_par_mois")){
                frequence_aeration_mois += 1;
            }
//            Question 29
            if(partie3.getEviter_trafic_velo().equals("oui+")){
                evite_pollution_souvent += 1;
            }else if(partie3.getEviter_trafic_velo().equals("oui")){
                evite_pollution_parfois += 1;
            }else if(partie3.getEviter_trafic_velo().equals("non")){
                evite_pollution_jamais += 1;
            }else if(partie3.getEviter_trafic_velo().equals("nonconcernée")){
                evite_pollution_pas_concerne += 1;
            }
//            Question 30
            if(partie3.getSport()){
                pratique_course_a_pied += 1;
            }
//            Question 31
            if(partie3.getSport_route_trafic()){
                evite_traffic_course_a_pied += 1;
            }
//            Question 32
            if(!partie3.getRemarques().equals("")){
                nbr_remarque += 1;
            }



        }

        context.setVariable("reponse_totale", reponse_totale);
//        Q°22
        context.setVariable("nbr_q_air_quartier_bonne", nbr_q_air_quartier_bonne);
        context.setVariable("nbr_q_air_quartier_jsp", nbr_q_air_quartier_jsp);
        context.setVariable("nbr_q_air_quartier_mauvaise", nbr_q_air_quartier_mauvaise);
        context.setVariable("nbr_q_air_quartier_p_bonne", nbr_q_air_quartier_p_bonne);
        context.setVariable("nbr_q_air_quartier_p_mauvaise", nbr_q_air_quartier_p_mauvaise);
//        Q°23
        context.setVariable("connais_pollution_ambiante", connais_pollution_ambiante);
//        Q°24
        context.setVariable("qualite_air_general", qualite_air_general);
        context.setVariable("prev_quotidienne_q_air_ext", prev_quotidienne_q_air_ext);
        context.setVariable("effet_pollution_sante", effet_pollution_sante);
        context.setVariable("recommandation_protection_pollution", recommandation_protection_pollution);
        context.setVariable("pics_pollution", pics_pollution);
        context.setVariable("precaution_pics_pollution", precaution_pics_pollution);
        context.setVariable("source_pollution_int", source_pollution_int);
        context.setVariable("source_pollution_ext", source_pollution_ext);
        context.setVariable("moyens_air_sain_int", moyens_air_sain_int);
        context.setVariable("moyens_air_sain_ext", moyens_air_sain_ext);
        context.setVariable("actions_publiques", actions_publiques);
//        Q°25
        context.setVariable("printemps", printemps);
        context.setVariable("ete", ete);
        context.setVariable("automne", automne);
        context.setVariable("hiver", hiver);
//        Q°26
        context.setVariable("fort_impact_sante", fort_impact_sante);
        context.setVariable("faible_impact_sante", faible_impact_sante);
        context.setVariable("pas_impact_sante", pas_impact_sante);
//        Q°27
        context.setVariable("aerer_logement", aerer_logement);
//        Q°28
        context.setVariable("frequence_aeration_1_semaine", frequence_aeration_1_semaine);
        context.setVariable("frequence_aeration_4_semaine", frequence_aeration_4_semaine);
        context.setVariable("frequence_aeration_jour", frequence_aeration_jour);
        context.setVariable("frequence_aeration_mois", frequence_aeration_mois);
//        Q°29
        context.setVariable("evite_pollution_jamais", evite_pollution_jamais);
        context.setVariable("evite_pollution_parfois", evite_pollution_parfois);
        context.setVariable("evite_pollution_pas_concerne", evite_pollution_pas_concerne);
        context.setVariable("evite_pollution_souvent", evite_pollution_souvent);
//        Q°30
        context.setVariable("pratique_course_a_pied", pratique_course_a_pied);
//        Q°31
        context.setVariable("evite_traffic_course_a_pied", evite_traffic_course_a_pied);
//        Q°32
        context.setVariable("nbr_remarque", nbr_remarque);


        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/visualisation_questionnaire", context, resp.getWriter());

    }

}
