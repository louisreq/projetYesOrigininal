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

@WebServlet(urlPatterns = {"/Prive/formulaire1", "/Admin/formulaire1"})
public class Formulaire1Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire1", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire1", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        String homme_femme = req.getParameter("homme_femme");
        String age = req.getParameter("quel_est_votre_age");
        String qui_etes_vous = req.getParameter("qui_etes_vous");
        String autre_texte = req.getParameter("autre");
        String retour_suivant = req.getParameter("retour_suivant");

        System.out.println("We have the sexe : " + homme_femme + "\n the age : " + age + "\n Who you are : " + qui_etes_vous);
        System.out.println(" Retour ou suivant ? -> " + retour_suivant);
        String template_to_load = "";
        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire";
        }
        else if(qui_etes_vous.equals("personnel")){
            template_to_load = "formulaire3";
            Boolean la_personne_fait_patrie_du_personnel = Boolean.TRUE;
            req.getSession().setAttribute("la_personne_fait_patrie_du_personnel", la_personne_fait_patrie_du_personnel);
        }else{
            template_to_load = "formulaire2";
            Boolean la_personne_fait_patrie_du_personnel = Boolean.FALSE;
            req.getSession().setAttribute("la_personne_fait_patrie_du_personnel", la_personne_fait_patrie_du_personnel);
        }

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/Prive/" + template_to_load);
        }
    }
}
