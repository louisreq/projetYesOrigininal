package hei.devweb.traderz.servlets;

import hei.devweb.traderz.managers.AdminManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pageAccueil")
public class PageAccueilServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String userconnected = (String) req.getSession().getAttribute("user");
        String hyerarchie = (String) req.getSession().getAttribute("hyerarchie");

        if (userconnected == null) { // l'utilisateur n'est pas connecté
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("PageAccueil", context, resp.getWriter());
        }else if(hyerarchie=="Administrateur") {
            resp.sendRedirect("/Admin/accueilAdmin");
        }else {
            resp.sendRedirect("/Prive/accueil"); // l'utilisateur est connecté
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("identifiant");
        String password = req.getParameter("mdp");
        String hyerarchie = req.getParameter("hyerarchie");
        String errorMessage = null;
        try {if(hyerarchie=="Utilisateur"){
            if (new UserManager().confirmPassword(username, password)){
                req.getSession().setAttribute("user", username);
                resp.sendRedirect("/Prive/accueil");
            } else {
                errorMessage = "Wrong password!";
            }
        }else {
            if(new AdminManager().confirmPassword(username, password)){
                req.getSession().setAttribute("user", username);
                resp.sendRedirect("/Admin/accueilAdmin");
            }else {
                errorMessage = "Wrong password!";
            }
        }

        } catch (Exception e ) {
            errorMessage = e.getMessage();
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("PageAccueil", context, resp.getWriter());
    }
}

