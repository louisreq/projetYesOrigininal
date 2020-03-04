package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CampusManager;
import hei.devweb.traderz.managers.FavoriManager;
import hei.devweb.traderz.managers.SalleManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/Prive/Add_Favoris", "/Admin/Add_Favoris"})
public class AddFavoriServlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Campus> liste_campus = CampusManager.getInstance().GetListOfCampus() ;

        for (Campus campus : liste_campus){
            List<Salle> salle_to_select = SalleManager.getInstance().GetListOfFavoriteSallesFromUserIdAndCampusId(user.getIdUser(), campus.getId());
            campus.setList_salles(salle_to_select);
        }

        context.setVariable("useronline", user );
        context.setVariable("liste_campus", liste_campus);

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Add_Favori", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Add_Favori", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());


        String principale_ou_secondaire = req.getParameter("importance");
        String salle_id = req.getParameter("selectsalle");

        FavoriManager.getInstance().AddFavori(user.getIdUser(), Integer.parseInt(principale_ou_secondaire), Integer.parseInt(salle_id));
        System.out.println("We just added Favorite \n" + principale_ou_secondaire + "\n for the room " + salle_id);

        if(user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/Liste_Favoris");
        }else {
            resp.sendRedirect("/traderz_war/Prive/Liste_Favoris");
        }
    }
}
