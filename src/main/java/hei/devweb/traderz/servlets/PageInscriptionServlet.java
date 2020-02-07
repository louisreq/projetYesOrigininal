package hei.devweb.traderz.servlets;

import hei.devweb.traderz.dao.UserDao;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/PageInscription")
public class PageInscriptionServlet extends PrivateServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        WebContext context = new WebContext (req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/WEB-INF/Templates/PageInscription", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String prenom = null;
        String nom = null;
        String mail = null;
        String sexe = null;
        String password = null;
        String confirmPassword = null;
        String role = "user";
        Boolean isEmailTaken = null;

        try {
            prenom = req.getParameter("prenom");
            nom = req.getParameter("nom");
            mail = req.getParameter("adresse_mail");
            sexe = req.getParameter("sexe");
            password = req.getParameter("password");
//            role = req.getParameter("role");
            confirmPassword = req.getParameter("password2");
            isEmailTaken = UserManager.getInstance().IsEmailAlreadyTaken(mail);
            System.out.println("Voici toutes les valeurs :\n - Prenom Nom = " + prenom + " " + nom);
            System.out.println("- Email = " + mail + "\n- sexe = " + sexe +"\n- Role = " + role);
            System.out.println("- Password1 = " + password + "\n- Password2 = " + confirmPassword);;
        } catch (NumberFormatException | DateTimeParseException ignored) {
        }

        Integer idUser = null;
        String errorMessage=null;
        User newUser = new User(idUser,nom,prenom,mail,sexe,password,role);
        try{
            if (password.equals(confirmPassword)) {
                System.out.println("Password confirmed");
                if (!isEmailTaken) {
                    System.out.println("Email is available");
                    User createdUser = UserManager.getInstance().addUser(newUser);
                    resp.sendRedirect("/PageConnexion");
                } else {
                    System.out.println("Email not evailable");
                    errorMessage = "Wrong characters";
                    resp.sendRedirect("/PageInscription");
                }
            }
        }catch (Exception e ) {
            errorMessage = e.getMessage();
        }
    }
}

