package hei.devweb.traderz.servlets;

import hei.devweb.traderz.dao.UserDao;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.User;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/pageInscription")
public class PageInscriptionServlet extends GenericServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        WebContext context = new WebContext (req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("PageInscription", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prenom = null;
        String nom = null;
        String mail = null;
        String mailBis = null;
        String sexe = null;
        String username = null;
        String password = null;
        String confirmPassword = null;
        LocalDate dateNaissance = null;
        double liquidites = 200000;
        double valeur = 0;

        try {
            prenom = req.getParameter("prenom");
            nom = req.getParameter("nom");
            mail = req.getParameter("mail");
            mailBis = req.getParameter("confirmMail");
            String releaseDateAsString = req.getParameter("dateNaissance");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateNaissance = LocalDate.parse(releaseDateAsString, dateFormat);
            sexe = req.getParameter("sexe");
            username = req.getParameter("identifiant");
            password = req.getParameter("mdp");
            confirmPassword = req.getParameter("confirmationMdp");


        } catch (NumberFormatException | DateTimeParseException ignored) {
        }

        Integer idUser = null;
        String errorMessage=null;
        User newUser = new User(idUser,prenom,nom,username,password,mail,dateNaissance,sexe,liquidites,valeur);
        try{
            if (UserManager.getInstance().verifyNewPassword(password,confirmPassword) & UserManager.getInstance().verifyNewPassword(mail,mailBis)){

                User createdUser= userDao.addUser(newUser);
                resp.sendRedirect("pageAccueil");
        } else {
               errorMessage = "Wrong characters";
               resp.sendRedirect("pageInscription");
            }
        }catch (Exception e ) {
            errorMessage = e.getMessage();
        }
        /*User newUser = new User(idUser,prenom,nom,username,password,mail,dateNaissance,sexe,liquidites,valeur);

        try {

            User createdUser= userDao.addUser(newUser);
                resp.sendRedirect("pageAccueil");

        } catch (IllegalArgumentException e){
            String errormessage = e.getMessage();
            resp.sendRedirect("pageInscription");
        }*/

    }
}

