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

@WebServlet(urlPatterns = {"/Prive/formulaire2", "/Admin/formulaire2"})
public class Formulaire2Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire2", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire2", context, resp.getWriter());
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
            template_to_load = "formulaire1";
        } else{

//            GET USER'S INPUT
            String quel_domaine = (req.getParameter("quel_domaine") != null ? req.getParameter("quel_domaine") : "");
            String autre_reponse = (req.getParameter("autre_reponse") != null ? req.getParameter("autre_reponse") : "");

            System.out.println("Voici le domaine " + quel_domaine + " Et voici l'autre réponse " + autre_reponse);

            if (quel_domaine.equals("autre")){
                quel_domaine = autre_reponse;
            }

            System.out.println("\nWe have the master study : " + quel_domaine);

            //   SET ALL ATTRIBUTES ANSWERED BY THE USER
            req.getSession().setAttribute("quel_domaine", quel_domaine);

            template_to_load = "formulaire3";
        }
        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load +"\n");

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
}
