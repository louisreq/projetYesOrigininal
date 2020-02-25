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

@WebServlet("/mdp_oublie")
public class MdpOublieServlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
//        System.out.println("\n\n This is a test in doget\n\n");
//        System.out.println("User connected email value : " + user_connected_email);
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/WEB-INF/Templates/MDP_Oublie", context, resp.getWriter());


//        if (user_connected_email == null) { // l'utilisateur n'est pas connecté
//
//        }else {
//            User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
//            if(user.getRole().equals("admin")){
//                resp.sendRedirect("/Admin/Home");
//            }else {
//                resp.sendRedirect("/Prive/Home"); // l'utilisateur est connecté
//            }
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
//        1) Check if the email exists
        Boolean is_email_taken = UserManager.getInstance().IsEmailAlreadyTaken(email);
        if(!is_email_taken){
//            Si le mail n'existe pas, on ne peut pas modifier le mdp
            String message_information = "L'utilisateur ayant l'adresse mail : " + email + " est inconnu !";
            req.getSession().setAttribute("message_information", message_information);
            resp.sendRedirect("/mdp_oublie");

        }else{
            //   2) Send an email telling the actual password + Tell to change mdp at the first connexion
        UserManager.getInstance().SendEmailForgotPassword(email);
        resp.sendRedirect("/PageConnexion");

        }
    }
}
