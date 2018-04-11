package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Admin;
import hei.devweb.traderz.managers.AdminManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Admin/gestionU")
public class GestionUServlet extends AdminPrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        Admin admin = AdminManager.getInstance().CreateAdminFromName(userconnected);
        context.setVariable("useronline  ", admin );

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("gestionU", context, resp.getWriter());
    }
}

