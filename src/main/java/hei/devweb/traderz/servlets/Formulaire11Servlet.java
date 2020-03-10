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

@WebServlet(urlPatterns = {"/Prive/formulaire11", "/Admin/formulaire11"})
public class Formulaire11Servlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Questionnaire/formulaire11", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Questionnaire/formulaire11", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        Boolean la_personne_aere_son_logement = (Boolean) req.getSession().getAttribute("la_personne_aere_son_logement");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        String Pratiquez_vous_course_a_pied = req.getParameter("Pratiquez_vous_course_a_pied");

        String retour_suivant = req.getParameter("retour_suivant");

        String template_to_load;

        // Set template to load
        if(retour_suivant.equals("Retour")){
            if(la_personne_aere_son_logement){
                template_to_load = "formulaire10";
            }else{
                template_to_load = "formulaire9";
            }
        } else if(Pratiquez_vous_course_a_pied.equals("non1")){
            String evitez_trafic_velo = req.getParameter("evitez_trafic_velo");
            req.getSession().setAttribute("evitez_trafic_velo", evitez_trafic_velo);
            System.out.println("evitez_trafic_velo : " + evitez_trafic_velo);
            template_to_load = "formulaire13";
            req.getSession().setAttribute("la_personne_pratique_la_course_a_pied", Boolean.FALSE);
        }else{
            String evitez_trafic_velo = req.getParameter("evitez_trafic_velo");
            req.getSession().setAttribute("evitez_trafic_velo", evitez_trafic_velo);
            System.out.println("evitez_trafic_velo : " + evitez_trafic_velo);
            template_to_load = "formulaire12";
            req.getSession().setAttribute("la_personne_pratique_la_course_a_pied", Boolean.TRUE);
        }

        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load);

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
}
