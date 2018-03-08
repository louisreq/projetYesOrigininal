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

@WebServlet("/Prive/modifmail")
public class ModifMailServlet extends PrivateServlet{

    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("modifmail", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newmail = req.getParameter("newmail");
        String confirmnewmail= req.getParameter("confirmnewmail");
        String username = (String) req.getSession().getAttribute("user");
        Boolean checkmail = UserManager.getInstance().verifyNewPassword(newmail, confirmnewmail);

        String errorMessage = null;
        try {
            if (checkmail) {
                userDao.modifyMail(newmail, username);
                resp.sendRedirect("/Prive/accueil");
            }

        }catch (Exception e ) {
            errorMessage = e.getMessage();
        }


        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("modifmail", context, resp.getWriter());
    }
}
