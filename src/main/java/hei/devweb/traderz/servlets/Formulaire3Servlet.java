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

@WebServlet(urlPatterns = {"/Prive/formulaire3", "/Admin/formulaire3"})
public class Formulaire3Servlet extends PrivateServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire3", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire3", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        Boolean la_personne_fait_patrie_du_personnel = (Boolean) req.getSession().getAttribute("la_personne_fait_patrie_du_personnel");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        String diplome = req.getParameter("diplome");
        String autre_reponse_diplome = req.getParameter("autre_reponse");
        String retour_suivant = req.getParameter("retour_suivant");

        System.out.println("We have the diplôme : " + diplome + "\n l'autre réponse est : " + autre_reponse_diplome);
        System.out.println(" Retour ou suivant ? -> " + retour_suivant);
        String template_to_load = "";
        // Set template to load
        if(retour_suivant.equals("Retour")){
            if(la_personne_fait_patrie_du_personnel){
                template_to_load = "formulaire1";
            }else {
                template_to_load = "formulaire2";
            }
        } else{
            template_to_load = "formulaire4";
        }

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/Prive/" + template_to_load);
        }
    }
}
