package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CotationManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Prive/cotationsEG")
public class CotationsEGServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.removeVariable("list");

        List<Cotation> cotationListEG = CotationManager.getInstance().listCotationEG();


        User user = UserManager.getInstance().CreateUserFromPseudo(userconnected);
        context.setVariable("useronline", user );
        context.setVariable("list", cotationListEG);


        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("cotations", context, resp.getWriter());
    }
}