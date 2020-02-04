package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Salle;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CampusManager;
import hei.devweb.traderz.managers.SalleManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Prive/Salle", "/Admin/Salle"})
public class SalleServlet extends PrivateServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        String id_selected_salle = req.getParameter("id_selected_salle");
        Salle selected_salle = SalleManager.getInstance().GetSalleFromId(Integer.parseInt(id_selected_salle));
        Campus campus_with_the_selected_salle = CampusManager.getInstance().GetCampusFromSalleId(Integer.parseInt(id_selected_salle));
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        context.setVariable("useronline", user );
        context.setVariable("selected_salle", selected_salle );
        context.setVariable("campus_with_the_selected_salle", campus_with_the_selected_salle);

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/salle", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/salle", context, resp.getWriter());
        }

    }
}
