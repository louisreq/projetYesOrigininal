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

@WebServlet(urlPatterns = {"/Prive/formulaire9", "/Admin/formulaire9"})
public class Formulaire9Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire9", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire9", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        Boolean la_personne_a_un_impact_sur_sa_sante = (Boolean) req.getSession().getAttribute("la_personne_a_un_impact_sur_sa_sante");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        String retour_suivant = req.getParameter("retour_suivant");

        String Aerez_vous_votre_logement = (req.getParameter("Aerez_vous_votre_logement") != null ? req.getParameter("Aerez_vous_votre_logement") : "");


        String template_to_load;

        // Set template to load
        if(retour_suivant.equals("Retour")){
            if(la_personne_a_un_impact_sur_sa_sante){
                template_to_load = "formulaire8";
            }else{
                template_to_load = "formulaire7";
            }
        } else if(Aerez_vous_votre_logement.equals("non")){
            template_to_load = "formulaire11";
            req.getSession().setAttribute("la_personne_aere_son_logement", Boolean.FALSE);
            req.getSession().setAttribute("Aerez_vous_votre_logement", Boolean.FALSE);
            System.out.println("la_personne_aere_son_logement : " + Aerez_vous_votre_logement);
        }else{
            template_to_load = "formulaire10";
            req.getSession().setAttribute("Aerez_vous_votre_logement", Boolean.TRUE);
            req.getSession().setAttribute("la_personne_aere_son_logement", Boolean.TRUE);
            System.out.println("la_personne_aere_son_logement : " + Aerez_vous_votre_logement);

        }

        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load);


        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
}
