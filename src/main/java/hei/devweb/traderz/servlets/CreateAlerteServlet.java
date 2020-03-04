package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.AlerteManager;
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
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/Prive/Create_Alerte", "/Admin/Create_Alerte"}) //"/Prive/Create_Alerte")
public class CreateAlerteServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Campus> liste_campus = CampusManager.getInstance().GetListOfCampus() ;

        for (Campus campus : liste_campus){
            campus.setList_salles( CampusManager.getInstance().GetListOfSalleWithCampusId(campus.getId()) );
        }

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        context.setVariable("useronline", user );
        context.setVariable("liste_campus", liste_campus);

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Create_Alerte", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Create_Alerte", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());

        Date date = new Date();
        Object datetime = new java.sql.Timestamp(date.getTime());
        String message = req.getParameter("Message_alerte");
        String salle_id = req.getParameter("selectbasic");
        String titre = req.getParameter("titre_alerte");

        AlerteManager.getInstance().AddAlerte(datetime, message, user.getIdUser(), Integer.parseInt(salle_id), titre, Boolean.FALSE);
        System.out.println("We just added the message \n" + message + "\n for the room " + salle_id);

        if(user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/Historique_Alerte");
        }else {
            resp.sendRedirect("/traderz_war/Prive/Historique_Alerte");
        }
    }
}
