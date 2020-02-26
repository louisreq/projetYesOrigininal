package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Formulaire_Partie1;
import hei.devweb.traderz.entities.Formulaire_Partie2;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.Formulaire_Partie1_Manager;
import hei.devweb.traderz.managers.Formulaire_Partie2_Manager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/Admin/Stats_Formulaire"})
public class StatsFormulaireServlet extends PrivateServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        List<Formulaire_Partie1> liste_form_partie_1 = Formulaire_Partie1_Manager.getInstance().GetAllFormPartie1();

//   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     PARTIE 1 QUESTIONNAIRE     <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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

    List<Formulaire_Partie2> liste_form_partie2 = Formulaire_Partie2_Manager.getInstance().GetAllFormPartie2();

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
    Double temp_min = -100000.;
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







        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/visualisation_questionnaire", context, resp.getWriter());

    }

}
