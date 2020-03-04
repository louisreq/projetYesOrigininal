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
        String retour_suivant = req.getParameter("retour_suivant");

        String homme_femme = req.getParameter("homme_femme");
        String age = req.getParameter("quel_est_votre_age");
        String qui_etes_vous = (req.getParameter("qui_etes_vous") != null ? req.getParameter("qui_etes_vous")  : "");
        String autre_texte = (req.getParameter("autre") != null ? req.getParameter("autre") : "");


        String template_to_load = "";
        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire";
        }
        else if(qui_etes_vous.equals("personnel")){
//            SET ALL ATTRIBUTES ANSWERED BY THE USER
            req.getSession().setAttribute("qui_etes_vous", qui_etes_vous);
            if(homme_femme.equals("homme")){
                req.getSession().setAttribute("homme_femme", 1);
            } else {
                req.getSession().setAttribute("homme_femme", 2);
            }
            req.getSession().setAttribute("age", age);

            template_to_load = "formulaire3";
            Boolean la_personne_fait_patrie_du_personnel = Boolean.TRUE;
            req.getSession().setAttribute("la_personne_fait_patrie_du_personnel", la_personne_fait_patrie_du_personnel);
        }else{
//            SET ALL ATTRIBUTES ANSWERED BY THE USER
            if(qui_etes_vous.equals("autre")){
                req.getSession().setAttribute("qui_etes_vous", autre_texte);
            }else{
                req.getSession().setAttribute("qui_etes_vous", qui_etes_vous);
            }
            if(homme_femme.equals("homme")){
                req.getSession().setAttribute("homme_femme", 1);
            } else {
                req.getSession().setAttribute("homme_femme", 2);
            }
            req.getSession().setAttribute("age", age);

            template_to_load = "formulaire2";
            Boolean la_personne_fait_patrie_du_personnel = Boolean.FALSE;
            req.getSession().setAttribute("la_personne_fait_patrie_du_personnel", la_personne_fait_patrie_du_personnel);
        }

        System.out.println("\nWe have the sexe : " + homme_femme + "\n the age : " + age + "\n Who you are : " + qui_etes_vous);

        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load + "\n");

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
}
