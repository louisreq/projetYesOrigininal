package hei.devweb.traderz.servlets;

import hei.devweb.traderz.dao.AdminDao;
import hei.devweb.traderz.dao.impl.AdminDaoImpl;
import hei.devweb.traderz.entities.Admin;
import hei.devweb.traderz.entities.User;
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
import java.util.List;

@WebServlet("/Admin/gestionU")
public class GestionUServlet extends AdminPrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");
        String usersByLetters = (String) req.getSession().getAttribute("usersByLetters");

            WebContext context = new WebContext(req, resp, req.getServletContext());
            List<User> userlist = new AdminDaoImpl().listOfUser();
            context.setVariable("users", userlist);

        Admin admin = AdminManager.getInstance().CreateAdminFromName(userconnected);
        context.setVariable("useronline  ", admin );
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("gestionU", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userid = req.getParameter("userId");
        Integer IdUser = Integer.parseInt(userid);
        /*String userletters = req.getParameter("champ");

        String check1 =  req.getParameter("check");
        Integer check2 = Integer.parseInt(check1);

        String try1 = req.getParameter("try");
        Integer try2 = Integer.parseInt(try1);*/
        String errorMessage = null;

        AdminDaoImpl admindao = new AdminDaoImpl();
        try {
            if(IdUser >0) {
                admindao.DeleteUser(IdUser);
                resp.sendRedirect("/Admin/gestionU");
            }
           /* if (check2>0){
                WebContext context = new WebContext(req, resp, req.getServletContext());
                context.setVariable("usersByLetters", userletters);
                resp.sendRedirect("/Admin/gestionU");
            }*/
        }catch (Exception e ) {
            errorMessage = e.getMessage();
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("gestionU", context, resp.getWriter());
    }

}

