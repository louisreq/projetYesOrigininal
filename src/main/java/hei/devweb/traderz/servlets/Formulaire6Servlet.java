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

@WebServlet(urlPatterns = {"/Prive/formulaire6", "/Admin/formulaire6"})
public class Formulaire6Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire6", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire6", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        String qualite_air_quartier = req.getParameter("qualite_air_quartier");
        String entendu_parler_pollution_de_fond = req.getParameter("entendu_parler_pollution_de_fond");
        String qualite_air_en_general = req.getParameter("qualite_air_en_general");
        String previsions_quotidiennes = req.getParameter("previsions_quotidiennes");
        String pollution_air_sur_la_sante = req.getParameter("pollution_air_sur_la_sante");
        String se_proteger_de_la_pollution_au_quotidien = req.getParameter("se_proteger_de_la_pollution_au_quotidien");
        String pics_de_pollution = req.getParameter("pics_de_pollution");
        String precaution_pics_de_pollution = req.getParameter("precaution_pics_de_pollution");
        String sources_de_pollution_air_interieur = req.getParameter("sources_de_pollution_air_interieur");
        String sources_de_pollution_air_exterieur = req.getParameter("sources_de_pollution_air_exterieur");
        String air_sain_interieur = req.getParameter("air_sain_interieur");
        String air_sain_exterieur = req.getParameter("air_sain_exterieur");
        String actions_publics_air = req.getParameter("actions_publics_air");
        String saison = req.getParameter("saison");

        String retour_suivant = req.getParameter("retour_suivant");

        System.out.println(" Retour ou suivant ? -> " + retour_suivant);
        String template_to_load;
        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire5";
        } else{
            template_to_load = "formulaire7";
        }

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/Prive/" + template_to_load);
        }
    }
}
