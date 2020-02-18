package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CampusManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/Prive/formulaire4", "/Admin/formulaire4"})

public class Formulaire4Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        List<Campus> liste_campus = CampusManager.getInstance().GetListOfCampus() ;

        for (Campus campus : liste_campus){
            campus.setList_salles( CampusManager.getInstance().GetListOfSalleWithCampusId(campus.getId()) );
        }

        context.setVariable("liste_campus", liste_campus);

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire4", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire4", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        String retour_suivant = req.getParameter("retour_suivant");

        String template_to_load = "";
        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire3";
        } else{
//      GET THE USER'S INPUT
            String qualite_air_hei = req.getParameter("qualité_air_hei");
            String salle_actuelle = req.getParameter("salle_actuelle");
            String fenetre_la_plus_proche = req.getParameter("fenetre_la_plus_proche");
            String distance_ventilation = req.getParameter("distance_ventilation");
            String temperature_espace_de_travail = req.getParameter("espace_de_travail");
            String confort_temperature = req.getParameter("confort_temperature");
            String temperature_capteur = req.getParameter("temperature_capteur");
            String humidity_air = req.getParameter("humidity_air");
            String confort_humidity_air = req.getParameter("confort_humidity_air");

            String[] odeur = req.getParameterValues("odeur");
            String liste_odeurs = "";
            for (String o : odeur){
                if(!o.equals("autre_odeur")) {
                    liste_odeurs += o + ", ";
                }
            }
            String autre_reponse_odeur = (req.getParameter("autre_reponse_odeur") != null ? req.getParameter("autre_reponse_odeur") : "");
            if (!autre_reponse_odeur.equals("")){
                liste_odeurs += autre_reponse_odeur;
            }else{
                liste_odeurs = liste_odeurs.substring(0, liste_odeurs.length() - 2);
            }

            String poussiere = req.getParameter("poussiere");
            String[] symptomes = req.getParameterValues("symptomes");
            String liste_symptomes = "";
            for(String s : symptomes){
                if(!s.equals("autre_symptome")){
                    liste_symptomes += s + ", ";
                }
            }
            String autre_reponse_symptome = (req.getParameter("autre_reponse_symptome") != null ? req.getParameter("autre_reponse_symptome") : "");
            if(!autre_reponse_symptome.equals("")){
                liste_symptomes += autre_reponse_symptome;
            }else{
                liste_symptomes = liste_symptomes.substring(0, liste_symptomes.length() - 2);
            }
//            String autre_reponse_symptome = req.getParameter("autre_reponse_symptome");
            String qualite_air_salle= req.getParameter("qualite_air_salle");

            System.out.println("\nWe have the air quality : " + qualite_air_hei);
            System.out.println("You selected the room with the Id : " + salle_actuelle);
            System.out.println("La fenêtre la plus proche est à : " + fenetre_la_plus_proche);
            System.out.println("Le ventilo le plus proche est à : " + distance_ventilation);
            System.out.println("La temperature est : " + temperature_espace_de_travail);
            System.out.println("Le confort de la température est plutôt : " + confort_temperature);
            System.out.println("La température relevée sur le capteur est de : " + temperature_capteur);
            System.out.println("L'humidité est plutôt : " + humidity_air);
            System.out.println("Quel est le niveau de confort d'humidité ? : " + confort_humidity_air);

            System.out.println("Voici la liste des choix sélectionnés pour les odeurs :" + liste_odeurs);
            System.out.println("Avez-vous de la poussière dans la salle ? : " + poussiere);
            System.out.println("Voici la liste des choix sélectionnés pour les Symptômes : " + liste_symptomes);
            System.out.println("Comment trouvez-vous la qualité de l'air ? : " + qualite_air_salle);
//            System.out.println("Voici la réponse Odeur Autre : " + autre_reponse_odeur);

            template_to_load = "formulaire5";

//            SET ALL ATTRIBUTES FROM USER'S ANSWERS
            req.getSession().setAttribute("qualite_air_hei", qualite_air_hei);
            req.getSession().setAttribute("salle_actuelle", salle_actuelle);
            req.getSession().setAttribute("fenetre_la_plus_proche", fenetre_la_plus_proche);
            req.getSession().setAttribute("distance_ventilation", distance_ventilation);
            req.getSession().setAttribute("temperature_espace_de_travail", temperature_espace_de_travail);
            req.getSession().setAttribute("confort_temperature", confort_temperature);
            req.getSession().setAttribute("temperature_capteur", temperature_capteur);
            req.getSession().setAttribute("humidity_air", humidity_air);
            req.getSession().setAttribute("confort_humidity_air", confort_humidity_air);
            req.getSession().setAttribute("liste_odeurs", liste_odeurs);
            req.getSession().setAttribute("poussiere", poussiere);
            req.getSession().setAttribute("liste_symptomes", liste_symptomes);
            req.getSession().setAttribute("qualite_air_salle", qualite_air_salle);
        }
        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load);

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/Prive/" + template_to_load);
        }
    }
}
