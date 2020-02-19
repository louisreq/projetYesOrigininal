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

@WebServlet(urlPatterns = {"/Prive/formulaire8", "/Admin/formulaire8"})
public class Formulaire8Servlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire8", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire8", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        String retour_suivant = req.getParameter("retour_suivant");



        String template_to_load;

        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire7";
        } else{
            String[] impacte_air_pollue = req.getParameterValues("impacte_air_pollue");
            String liste_impact_air_pollue = "";
            for(String impact : impacte_air_pollue){
                liste_impact_air_pollue += impact + ", ";
            }
            liste_impact_air_pollue = liste_impact_air_pollue.substring(0, liste_impact_air_pollue.length() - 2);
            req.getSession().setAttribute("impacte_air_pollue", liste_impact_air_pollue);
            System.out.println("impacte_air_pollue : " + liste_impact_air_pollue);
            template_to_load = "formulaire9";
        }
        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load);

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/Prive/" + template_to_load);
        }
    }
    }

