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

@WebServlet("/Prive/Salle")
public class SalleServlet extends PrivateServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        String id_selected_salle = req.getParameter("id_selected_salle");

        WebContext context = new WebContext(req, resp, req.getServletContext());
//        id_selected_salle

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
        context.setVariable("useronline", user );
        context.setVariable("id_selected_salle", id_selected_salle );

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("salle", context, resp.getWriter());
    }
}
