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
import java.util.List;

@WebServlet(urlPatterns = {"/Admin/Gestion_Admin"})

public class GestionAdminServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        String message_information_apres_create_admin = (String) req.getSession().getAttribute("message_information_apres_create_admin");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        List<User> list_of_admin = UserManager.getInstance().GetAllAdmin();

        context.setVariable("list_of_admin", list_of_admin);
        context.setVariable("message_information_apres_create_admin", message_information_apres_create_admin);
        req.getSession().removeAttribute("message_information_apres_create_admin");

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("/WEB-INF/Templates/Admin/Gestion_Admin", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user_connected = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

//        CETTE LONGUE INITIALISATION PERMET DE NE PAS AVOIR DE VARIABLES NULLES ET D'EVITER LE FAMEUX NULLPOINTER EXCEPTION ERROR
        String adresse_mail_create_admin = ((adresse_mail_create_admin = req.getParameter("adresse_mail_create_admin")) != null) ? adresse_mail_create_admin : "";
        String adresse_mail_delete_user = ((adresse_mail_delete_user = req.getParameter("adresse_mail_delete_user")) != null) ? adresse_mail_delete_user : "";

        //        Get element from the searching bar
        String text_searched = ((text_searched = req.getParameter("text_searched")) != null) ? text_searched : "";

        System.out.println(text_searched);
        if(!text_searched.equals("")){
            req.getSession().setAttribute("user_searched_from_salle", text_searched);
            resp.sendRedirect("/traderz_war/Admin/Home");

        }else{

        System.out.println("Admin créé : " + adresse_mail_create_admin);
        System.out.println("Admin supprimé : " + adresse_mail_delete_user);

        if(!adresse_mail_create_admin.equals("")){
//            ICI ON FAIT PASSER UN USER AU STADE ADMIN
            Boolean is_this_email_valid = UserManager.getInstance().IsEmailAlreadyTaken(adresse_mail_create_admin);

            String message_information_apres_create_admin;
            if (is_this_email_valid) {
                User user_selected = UserManager.getInstance().CreateUserFromEmail(adresse_mail_create_admin);
                UserManager.getInstance().TurnUserToAdmin(user_selected.getIdUser());
                message_information_apres_create_admin = "La personne ayant le mail : " + user_selected.getMail() + " a bien été passé en administrateur" ;
            }else{
                message_information_apres_create_admin = "L'email renseigne n'est pas valide. Par consequent aucun administrateur a ete ajoute" ;
            }
            System.out.println(message_information_apres_create_admin);

            req.getSession().setAttribute("message_information_apres_create_admin", message_information_apres_create_admin );

        }
        if(!adresse_mail_delete_user.equals("")){
//            ICI ON FAIT PASSER UN ADMIN A SIMPLE USER

            User user_selected = UserManager.getInstance().CreateUserFromEmail(adresse_mail_delete_user);
            UserManager.getInstance().TurnAdminToUser(user_selected.getIdUser());
        }



        resp.sendRedirect("/traderz_war/Admin/Gestion_Admin");
    }
    }
}
