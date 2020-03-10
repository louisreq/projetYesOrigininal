package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Favori;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.FavoriManager;
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

@WebServlet(urlPatterns = {"/Prive/Liste_Favoris", "/Admin/Liste_Favoris"})
public class ListeFavorisServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");


        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");

        WebContext context = new WebContext(req, resp, req.getServletContext());


        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        context.setVariable("useronline", user );

        List<Favori> liste_all_favoris = FavoriManager.getInstance().GetListOfFavorisFromUserId(user.getIdUser());
        List<Favori> liste_principale = new ArrayList<>();
        List<Favori> liste_secondaire = new ArrayList<>();

        for (Favori favori : liste_all_favoris){
            if (favori.getPrincipale_or_secondaire().equals("Principale")){
                liste_principale.add(favori);
            }else{
                liste_secondaire.add(favori);
            }
        }
        context.setVariable("principaux_favoris", liste_principale );
        context.setVariable("secondaires_favoris", liste_secondaire );

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Liste_Favoris", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Liste_Favoris", context, resp.getWriter());
        }
    }
}
