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
        resp.setContentType("text/html;charset=utf-8");

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
        String retour_suivant = req.getParameter("retour_suivant");


        String template_to_load;
        // Set template to load
        if(retour_suivant.equals("Retour")){
            template_to_load = "formulaire5";
        } else{

//          GET USER'S ANSWERS
            String qualite_air_quartier = req.getParameter("qualite_air_quartier");
            Boolean entendu_parler_pollution_de_fond = (req.getParameter("entendu_parler_pollution_de_fond").contains("oui") ? Boolean.TRUE : Boolean.FALSE); //BOOLEAN
            Boolean qualite_air_en_general = (req.getParameter("qualite_air_en_general").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean previsions_quotidiennes = (req.getParameter("previsions_quotidiennes").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean pollution_air_sur_la_sante = (req.getParameter("pollution_air_sur_la_sante").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean se_proteger_de_la_pollution_au_quotidien = (req.getParameter("se_proteger_de_la_pollution_au_quotidien").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean pics_de_pollution = (req.getParameter("pics_de_pollution").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean precaution_pics_de_pollution = (req.getParameter("precaution_pics_de_pollution").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean sources_de_pollution_air_interieur = (req.getParameter("sources_de_pollution_air_interieur").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean sources_de_pollution_air_exterieur = (req.getParameter("sources_de_pollution_air_exterieur").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean air_sain_interieur = (req.getParameter("air_sain_interieur").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean air_sain_exterieur =(req.getParameter("air_sain_exterieur").contains("oui") ? Boolean.TRUE : Boolean.FALSE);
            Boolean actions_publics_air = (req.getParameter("actions_publics_air").contains("oui") ? Boolean.TRUE : Boolean.FALSE); //BOOLEAN
            String[] saison = req.getParameterValues("saison");
            String liste_saison = "";
            for(String s : saison){
                liste_saison += s + ", ";
            }
            liste_saison = liste_saison.substring(0, liste_saison.length() - 2);


            template_to_load = "formulaire7";
//            SET ATTRIBUTES WITH ANSWERS
            req.getSession().setAttribute("qualite_air_quartier", qualite_air_quartier);
            req.getSession().setAttribute("entendu_parler_pollution_de_fond", entendu_parler_pollution_de_fond);
            req.getSession().setAttribute("qualite_air_en_general", qualite_air_en_general);
            req.getSession().setAttribute("previsions_quotidiennes", previsions_quotidiennes);
            req.getSession().setAttribute("pollution_air_sur_la_sante", pollution_air_sur_la_sante);
            req.getSession().setAttribute("se_proteger_de_la_pollution_au_quotidien", se_proteger_de_la_pollution_au_quotidien);
            req.getSession().setAttribute("pics_de_pollution", pics_de_pollution);
            req.getSession().setAttribute("precaution_pics_de_pollution", precaution_pics_de_pollution);
            req.getSession().setAttribute("sources_de_pollution_air_interieur", sources_de_pollution_air_interieur);
            req.getSession().setAttribute("sources_de_pollution_air_exterieur", sources_de_pollution_air_exterieur);
            req.getSession().setAttribute("air_sain_interieur", air_sain_interieur);
            req.getSession().setAttribute("air_sain_exterieur", air_sain_exterieur);
            req.getSession().setAttribute("actions_publics_air", actions_publics_air);
            req.getSession().setAttribute("liste_saison", liste_saison);

//            Print all of them
            System.out.println("\nqualite_air_quartier : " + qualite_air_quartier);
            System.out.println("entendu_parler_pollution_de_fond : " + entendu_parler_pollution_de_fond);
            System.out.println("qualite_air_en_general : " + qualite_air_en_general);
            System.out.println("previsions_quotidiennes : " + previsions_quotidiennes);
            System.out.println("pollution_air_sur_la_sante : " + pollution_air_sur_la_sante);
            System.out.println("se_proteger_de_la_pollution_au_quotidien : " + se_proteger_de_la_pollution_au_quotidien);
            System.out.println("pics_de_pollution : " + pics_de_pollution);
            System.out.println("precaution_pics_de_pollution : " + precaution_pics_de_pollution);
            System.out.println("sources_de_pollution_air_interieur : " + sources_de_pollution_air_interieur);
            System.out.println("sources_de_pollution_air_exterieur : " + sources_de_pollution_air_exterieur);
            System.out.println("air_sain_interieur : " + air_sain_interieur);
            System.out.println("air_sain_exterieur : " + air_sain_exterieur);
            System.out.println("actions_publics_air : " + actions_publics_air);
            System.out.println("liste_saison : " + liste_saison);
        }
        System.out.println(" Retour ou suivant ? -> " + retour_suivant + " " + template_to_load);

        if (user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/" + template_to_load);
        }else{
            resp.sendRedirect("/traderz_war/Prive/" + template_to_load);
        }
    }
}
