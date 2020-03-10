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

@WebServlet(urlPatterns = {"/Prive/formulaire7", "/Admin/formulaire7"})
public class Formulaire7Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire7", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire7", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        String qualite_air_un_impact_sur_la_sante = req.getParameter("qualite_air_un_impact_sur_la_sante");

        String retour_suivant = req.getParameter("retour_suivant");

        String template_to_load;

        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire6";
        } else if(qualite_air_un_impact_sur_la_sante.equals("Non")) {
            template_to_load = "formulaire9";
            req.getSession().setAttribute("la_personne_a_un_impact_sur_sa_sante", Boolean.FALSE);
            req.getSession().setAttribute("qualite_air_un_impact_sur_la_sante", qualite_air_un_impact_sur_la_sante);
            System.out.println("\nqualite_air_un_impact_sur_la_sante : " + qualite_air_un_impact_sur_la_sante);
        }else{
            template_to_load = "formulaire8";
            req.getSession().setAttribute("la_personne_a_un_impact_sur_sa_sante", Boolean.TRUE);
            req.getSession().setAttribute("qualite_air_un_impact_sur_la_sante", qualite_air_un_impact_sur_la_sante);
            System.out.println("\nqualite_air_un_impact_sur_la_sante : " + qualite_air_un_impact_sur_la_sante);
        }
        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load + "\n");

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
    }

