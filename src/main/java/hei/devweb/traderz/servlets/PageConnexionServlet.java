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

@WebServlet("/PageConnexion")
public class PageConnexionServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        System.out.println("\n\n This is a test in doget\n\n");
        System.out.println("User connected email value : " + user_connected_email);

        if (user_connected_email == null) { // l'utilisateur n'est pas connecté
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("PageConnexion", context, resp.getWriter());
        }else {
            User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
            if(user.getRole().equals("admin")){
                resp.sendRedirect("/Admin/Home");
            }else {
                resp.sendRedirect("/Prive/Home"); // l'utilisateur est connecté
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(email);
        System.out.println("\n\n This is a test in DOPOST \n\n");
        String errorMessage = null;
        try {
            if (new UserManager().confirmPassword(email, password)){
                req.getSession().setAttribute("user_connected_email", email);
                User user = UserManager.getInstance().CreateUserFromEmail(email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session
                if(user.getRole().equals("admin")){
                    resp.sendRedirect("/Admin/Home");
                }else {
                    resp.sendRedirect("/Prive/Home"); // l'utilisateur est connecté
                }
            } else {
                errorMessage = "Wrong password!";
            }

        } catch (Exception e ) {
            errorMessage = e.getMessage();
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/PageConnexion", context, resp.getWriter());
    }
}

