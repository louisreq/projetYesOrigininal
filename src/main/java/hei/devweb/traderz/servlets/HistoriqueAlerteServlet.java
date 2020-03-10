package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Alerte;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.AlerteManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import static java.nio.charset.StandardCharsets.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@WebServlet(urlPatterns = {"/Prive/Historique_Alerte", "/Admin/Historique_Alerte"})
public class HistoriqueAlerteServlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        String id_selected_salle = req.getParameter("id_selected_salle");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        List<Alerte> liste_alertes = AlerteManager.getInstance().GetAlertesFromUserId(user.getIdUser());

        for(Alerte alerte : liste_alertes){
            System.out.println("This is the message of the alerte :\n" + alerte.getMessage()    );
        }

        String message_test = "Voilà un méssage";

        byte[] ptext = message_test.getBytes(ISO_8859_1);
        String message_test_edited = new String(ptext, UTF_8);

        context.setVariable("message_test", message_test);
        context.setVariable("message_test_edited", message_test_edited);

        context.setVariable("useronline", user );
        context.setVariable("id_selected_salle", id_selected_salle );
        context.setVariable("liste_alertes", liste_alertes);

        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Historique_Alerte", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Historique_Alerte", context, resp.getWriter());
        }
    }
}
