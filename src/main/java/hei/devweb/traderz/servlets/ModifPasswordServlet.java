package hei.devweb.traderz.servlets;

import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Prive/modifpassword")
public class ModifPasswordServlet extends PrivateServlet {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("modifpassword", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newmdp = req.getParameter("nouveaumdp");
        String confirmnewmdp = req.getParameter("confirmnouveaumdp");
        String errorMessage = null;
        String username = (String) req.getSession().getAttribute("user");

        Boolean checkmdp = UserManager.getInstance().verifyNewPassword(newmdp, confirmnewmdp);

        try {
            if (checkmdp) {
                userDao.modifyPassword(newmdp, username);
                resp.sendRedirect("/Prive/accueil");
            }

        }catch (Exception e ) {
            errorMessage = e.getMessage();
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("modifpassword", context, resp.getWriter());
    }
}
