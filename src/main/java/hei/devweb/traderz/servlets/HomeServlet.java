package hei.devweb.traderz.servlets;


import hei.devweb.traderz.dao.SalleDao;
import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CampusManager;
import hei.devweb.traderz.managers.SalleManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/Prive/Home", "/Admin/Home"})
public class HomeServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String user_searched_from_salle = (String) req.getSession().getAttribute("user_searched_from_salle");
//        String test = (user_searched_from_salle == null ? text_searched : user_searched_from_salle);

//        Get element from the searching bar
        String text_searched = ((text_searched = req.getParameter("text_searched")) != null) ? text_searched : "";

        text_searched = (user_searched_from_salle == null ? text_searched : user_searched_from_salle);
//        Get the campus clicked
        String campus_clicked = ((campus_clicked = req.getParameter("campus_selected")) != null) ? campus_clicked : "";
        System.out.println("\n\n On a CLIQUE sur " + campus_clicked + " ->" + " !!\n\n");

        System.out.println("\n\n On vient tout juste de chercher " + text_searched + " ->" + " !!\n\n");
        List<Campus> liste_campus = new ArrayList<>();
        if(!campus_clicked.equals("")){
            liste_campus.add(CampusManager.getInstance().GetListOfSalleWithCampusName(campus_clicked));

        }else{
            //        On Récupère les Campus
            liste_campus = CampusManager.getInstance().GetListOfCampus() ;

//        On loop sur les campus pour trouver des salles qui matchent avec la saisie de l'utilisateur
//        On ajoute ensuite cette liste de salles dans le campus
            List<Campus> campus_to_drop = new ArrayList<>();
            for (Campus campus : liste_campus){
                if (!text_searched.isEmpty()){
                    System.out.println("\n We search for text_input = " + text_searched + " in the Campus " + campus.getCampus_name());
                    List<Salle> liste_salles = SalleManager.getInstance().GetListOfSalleFromCampusAndUserInput(campus.getId(), text_searched);
                    System.out.println(liste_salles);
                    campus.setList_salles(liste_salles);
                    System.out.println("This is the liste SIZE : " + liste_salles.size());
                    if(liste_salles.size()==0){
                        campus_to_drop.add(campus);
                    }
                }else {
                    liste_campus = new ArrayList<>();
                }
            }
            for (Campus campus : campus_to_drop){
                liste_campus.remove(campus);
            }
            System.out.println("Campus à droper : " + campus_to_drop);
            System.out.println("\nCampus à garder : " + liste_campus);
        }


        context.setVariable("liste_campus", liste_campus);
        context.setVariable("text_searched", text_searched);
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");



        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        context.setVariable("useronline", user );

//        System.out.println(user_searched_from_salle);
        req.getSession().removeAttribute("user_searched_from_salle");
        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Admin/home", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/home", context, resp.getWriter());
        }
    }
}
