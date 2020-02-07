package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/Prive/formulaire4", "/Admin/formulaire4"})

public class Formulaire4Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

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

        String qualite_air_hei = req.getParameter("qualité_air_hei");
        String salle = req.getParameter("salle");
        String fenetre_la_plus_proche = req.getParameter("fenetre_la_plus_proche");
        String distance_ventilation = req.getParameter("distance_ventilation");
        String temperature_espace_de_travail = req.getParameter("espace_de_travail");
        String confort_temperature = req.getParameter("confort_temperature");
        String temperature_capteur = req.getParameter("temperature_capteur");
        String humidity_air = req.getParameter("humidity_air");
        String confort_humidity_air = req.getParameter("confort_humidity_air");
        String odeur = req.getParameter("odeur");
        String autre_reponse_odeur = req.getParameter("autre_reponse_odeur");
        String poussiere = req.getParameter("poussière");
        String symptomes = req.getParameter("symptômes");
        String autre_reponse_symptome = req.getParameter("autre_reponse_symptome");
        String qualite_air_salle= req.getParameter("qualite_air_salle");


        String retour_suivant = req.getParameter("retour_suivant");

        System.out.println("We have the air quality : " + qualite_air_hei);
        System.out.println(" Retour ou suivant ? -> " + retour_suivant);
        String template_to_load = "";
        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire3";
        } else{
            template_to_load = "formulaire5";
        }

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/Prive/" + template_to_load);
        }
    }
}
