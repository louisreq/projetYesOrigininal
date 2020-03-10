package hei.devweb.traderz.servlets;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/Prive/formulaire14", "/Admin/formulaire14"})
public class Formulaire14Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire14", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire14", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        String valider_formulaire = (req.getParameter("valider_formulaire") != null ? req.getParameter("valider_formulaire") : "");

        System.out.println(" valider_formulaire ? -> " + valider_formulaire);
        String template_to_load = "";

        // Set template to load
        if(!valider_formulaire.equals("")){

//~~~~~~~~~~~~~~~~     GET ALL ATTRIBUTES FROM PREVIOUS PAGES      ~~~~~~~~~~~~~~~~~~
//            Page 1
            Integer homme_femme = (Integer) req.getSession().getAttribute("homme_femme");
            String age = (String) req.getSession().getAttribute("age");
            String qui_etes_vous = (String) req.getSession().getAttribute("qui_etes_vous");

//            Page 2 optionnel
            String quel_domaine = (String) (req.getSession().getAttribute("quel_domaine") != null ? req.getSession().getAttribute("quel_domaine") : "");

//            Page 3
            String diplome = (String) req.getSession().getAttribute("diplome");
            String quelle_ville = (String) req.getSession().getAttribute("quelle_ville");
            Boolean is_Parent = (Boolean) req.getSession().getAttribute("is_Parent");
            String mot1 = (String) req.getSession().getAttribute("mot1");
            String mot2 = (String) req.getSession().getAttribute("mot2");
            String mot3 = (String) req.getSession().getAttribute("mot3");

//            Page 4
            String qualite_air_hei = (String) req.getSession().getAttribute("qualite_air_hei");
            Integer salle_actuelle = (Integer) req.getSession().getAttribute("salle_actuelle");
            String fenetre_la_plus_proche = (String) req.getSession().getAttribute("fenetre_la_plus_proche");
            String distance_ventilation = (String) req.getSession().getAttribute("distance_ventilation");
            String temperature_espace_de_travail = (String) req.getSession().getAttribute("temperature_espace_de_travail");
            String confort_temperature = (String) req.getSession().getAttribute("confort_temperature");
            String temperature_capteur = (String) req.getSession().getAttribute("temperature_capteur");
            String humidity_air = (String) req.getSession().getAttribute("humidity_air");
            String confort_humidity_air = (String) req.getSession().getAttribute("confort_humidity_air");
            String liste_odeurs = (String) req.getSession().getAttribute("liste_odeurs");
            Boolean poussiere = (Boolean) req.getSession().getAttribute("poussiere");
            String liste_symptomes = (String) req.getSession().getAttribute("liste_symptomes");
            String qualite_air_salle = (String) req.getSession().getAttribute("qualite_air_salle");

//            Page 5
//            RIEN A FAIRE

//            Page 6
            String qualite_air_quartier = (String) req.getSession().getAttribute("qualite_air_quartier");
            Boolean entendu_parler_pollution_de_fond = (Boolean) req.getSession().getAttribute("entendu_parler_pollution_de_fond"); //BOOLEAN
            Boolean qualite_air_en_general = (Boolean) req.getSession().getAttribute("qualite_air_en_general");
            Boolean previsions_quotidiennes = (Boolean) req.getSession().getAttribute("previsions_quotidiennes");
            Boolean pollution_air_sur_la_sante = (Boolean) req.getSession().getAttribute("pollution_air_sur_la_sante");
            Boolean se_proteger_de_la_pollution_au_quotidien = (Boolean) req.getSession().getAttribute("se_proteger_de_la_pollution_au_quotidien");
            Boolean pics_de_pollution = (Boolean) req.getSession().getAttribute("pics_de_pollution");
            Boolean precaution_pics_de_pollution = (Boolean) req.getSession().getAttribute("precaution_pics_de_pollution");
            Boolean sources_de_pollution_air_interieur = (Boolean) req.getSession().getAttribute("sources_de_pollution_air_interieur");
            Boolean sources_de_pollution_air_exterieur = (Boolean) req.getSession().getAttribute("sources_de_pollution_air_exterieur");
            Boolean air_sain_interieur = (Boolean) req.getSession().getAttribute("air_sain_interieur");
            Boolean air_sain_exterieur = (Boolean) req.getSession().getAttribute("air_sain_exterieur");
            Boolean actions_publics_air = (Boolean) req.getSession().getAttribute("actions_publics_air"); // BOOLEAN
            String liste_saison = (String) req.getSession().getAttribute("liste_saison");

//            Page 7
            String qualite_air_un_impact_sur_la_sante = (String) (req.getSession().getAttribute("qualite_air_un_impact_sur_la_sante") != null ? req.getSession().getAttribute("qualite_air_un_impact_sur_la_sante") : "");

//            Page 8
            String impacte_air_pollue = (String) (req.getSession().getAttribute("impacte_air_pollue") != null ? req.getSession().getAttribute("impacte_air_pollue") : "");

//            Page 9
            Boolean Aerez_vous_votre_logement = (Boolean) (req.getSession().getAttribute("Aerez_vous_votre_logement") != null ? req.getSession().getAttribute("Aerez_vous_votre_logement") : "");

//            Page 10
            String frequence_aeration = (String) (req.getSession().getAttribute("frequence_aeration") != null ? req.getSession().getAttribute("frequence_aeration") : "");

//            Page 11
            Boolean la_personne_pratique_la_course_a_pied = (Boolean) req.getSession().getAttribute("la_personne_pratique_la_course_a_pied");
            String evitez_trafic_velo = (String) req.getSession().getAttribute("evitez_trafic_velo");

//            Page 12
            Boolean evitez_le_sport_traffic = (Boolean) req.getSession().getAttribute("evitez_le_sport_traffic");

//            Page 13
            String remarques = (String) (req.getSession().getAttribute("remarques") != null ? req.getSession().getAttribute("remarques") : "");

//~~~~~~~~~~~~~~    DELETE ALL ATTRIBUTES    ~~~~~~~~~~~~~~
//            Page 1
            req.getSession().removeAttribute("homme_femme");
            req.getSession().removeAttribute("age");
            req.getSession().removeAttribute("qui_etes_vous");
//            Page 2 OPTIONNEL
            req.getSession().removeAttribute("quel_domaine");
//            Page 3
            req.getSession().removeAttribute("diplome");
            req.getSession().removeAttribute("quelle_ville");
            req.getSession().removeAttribute("is_Parent");
            req.getSession().removeAttribute("mot1");
            req.getSession().removeAttribute("mot2");
            req.getSession().removeAttribute("mot3");
//            Page 4
            req.getSession().removeAttribute("qualite_air_hei");
            req.getSession().removeAttribute("salle_actuelle");
            req.getSession().removeAttribute("fenetre_la_plus_proche");
            req.getSession().removeAttribute("distance_ventilation");
            req.getSession().removeAttribute("temperature_espace_de_travail");
            req.getSession().removeAttribute("confort_temperature");
            req.getSession().removeAttribute("temperature_capteur");
            req.getSession().removeAttribute("humidity_air");
            req.getSession().removeAttribute("confort_humidity_air");
            req.getSession().removeAttribute("humidity_air");
            req.getSession().removeAttribute("liste_odeurs");
            req.getSession().removeAttribute("poussiere");
            req.getSession().removeAttribute("liste_symptomes");
            req.getSession().removeAttribute("qualite_air_salle");
//            Page 5
//            RIEN A FAIRE
//            Page 6
            req.getSession().removeAttribute("qualite_air_quartier");
            req.getSession().removeAttribute("entendu_parler_pollution_de_fond");
            req.getSession().removeAttribute("qualite_air_en_general");
            req.getSession().removeAttribute("previsions_quotidiennes");
            req.getSession().removeAttribute("pollution_air_sur_la_sante");
            req.getSession().removeAttribute("se_proteger_de_la_pollution_au_quotidien");
            req.getSession().removeAttribute("pics_de_pollution");
            req.getSession().removeAttribute("precaution_pics_de_pollution");
            req.getSession().removeAttribute("sources_de_pollution_air_interieur");
            req.getSession().removeAttribute("sources_de_pollution_air_exterieur");
            req.getSession().removeAttribute("air_sain_interieur");
            req.getSession().removeAttribute("air_sain_exterieur");
            req.getSession().removeAttribute("actions_publics_air");
            req.getSession().removeAttribute("liste_saison");
//            Page 7
            req.getSession().removeAttribute("qualite_air_un_impact_sur_la_sante");
//            Page 8
            req.getSession().removeAttribute("impacte_air_pollue");
//            Page 9
            req.getSession().removeAttribute("Aerez_vous_votre_logement");
//            Page 10
            req.getSession().removeAttribute("frequence_aeration");
//            Page 11
            req.getSession().removeAttribute("la_personne_pratique_la_course_a_pied");
            req.getSession().removeAttribute("evitez_trafic_velo");
//            Page 12
            req.getSession().removeAttribute("evitez_le_sport_traffic");
//            Page 13
            req.getSession().removeAttribute("remarques");

//            INSERER LES REPONSES DANS LES 3 TABLES DE LA BDD CORRESPONDANTES
            Date date = new Date();
            Object datetime = new java.sql.Timestamp(date.getTime());

            UserManager.getInstance().AddQuestionnairePartieInfoPerso(user.getIdUser(), homme_femme,
                    Integer.parseInt(age), qui_etes_vous, quel_domaine, diplome,
                    is_Parent, quelle_ville, mot1, mot2, mot3, datetime);

            UserManager.getInstance().AddQuestionnairePartieSensationSalle(
                    salle_actuelle, user.getIdUser(), 1,
                    qualite_air_hei, fenetre_la_plus_proche, distance_ventilation,
                    temperature_espace_de_travail, confort_temperature, temperature_capteur,
                    humidity_air, confort_humidity_air, liste_odeurs,
                    poussiere, liste_symptomes, qualite_air_salle, datetime
                    );

            UserManager.getInstance().AddQuestionnairePartieInfoSensibilisation(
                    user.getIdUser(), qualite_air_quartier, entendu_parler_pollution_de_fond, qualite_air_en_general,
                    previsions_quotidiennes, pollution_air_sur_la_sante, se_proteger_de_la_pollution_au_quotidien,
                    pics_de_pollution, precaution_pics_de_pollution, sources_de_pollution_air_interieur,
                    sources_de_pollution_air_exterieur, air_sain_interieur, air_sain_exterieur, actions_publics_air,
                    liste_saison, qualite_air_un_impact_sur_la_sante, impacte_air_pollue, Aerez_vous_votre_logement,
                    frequence_aeration, evitez_trafic_velo, la_personne_pratique_la_course_a_pied,
                    evitez_le_sport_traffic, remarques, datetime
            );

            template_to_load = "Home";
        }

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
}
