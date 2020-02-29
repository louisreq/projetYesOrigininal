package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Alerte;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.AlerteManager;
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
import java.util.Map;


@WebServlet(urlPatterns = {"/Admin/Alertes_Utilisateurs"})
public class AlerteUtilisateurServlet extends PrivateServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");

        WebContext context = new WebContext(req, resp, req.getServletContext());


        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        context.setVariable("useronline", user );

        List<Alerte> liste_alerte = AlerteManager.getInstance().GetAllAlertes();
        List<Alerte> liste_checked_alerte = new ArrayList<>();
        List<Alerte> liste_unchecked_alerte = new ArrayList<>();
        for(Alerte alerte : liste_alerte){
            if(alerte.getChecked()){
                liste_checked_alerte.add(alerte);
            }else{
                liste_unchecked_alerte.add(alerte);
            }
        }



        Map<Integer, String> mapping_id_salle_name = SalleManager.getInstance().GetAllSallesMapedWithIdAndCampusName();


        context.setVariable("liste_checked_alerte", liste_checked_alerte);
        context.setVariable("liste_unchecked_alerte", liste_unchecked_alerte);
        context.setVariable("mapping_id_salle_name", mapping_id_salle_name);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/WEB-INF/Templates/Admin/alertes_utilisateur", context, resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());


        String switch_checkbox = (req.getParameter("switch_checkbox") != null ? req.getParameter("switch_checkbox") : "" );
        String alerte_id = (req.getParameter("alerte_id") != null ? req.getParameter("alerte_id") : "");

        if(switch_checkbox.equals("yes")){
            AlerteManager.getInstance().SetAlerteChecked(Integer.parseInt(alerte_id), Boolean.TRUE);
        }else if(switch_checkbox.equals("no")){
            AlerteManager.getInstance().SetAlerteChecked(Integer.parseInt(alerte_id), Boolean.FALSE);
        }

//        FavoriManager.getInstance().AddFavori(user.getIdUser(), Integer.parseInt(principale_ou_secondaire), Integer.parseInt(salle_id));
        System.out.println("We just switched checked  \n" + switch_checkbox + "\n for the alerte " + alerte_id);


        resp.sendRedirect("/Admin/Alertes_Utilisateurs");
    }
}
