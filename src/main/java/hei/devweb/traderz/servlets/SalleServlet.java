package hei.devweb.traderz.servlets;

//import com.sun.org.apache.xpath.internal.operations.Bool;
//import com.sun.org.apache.xpath.internal.operations.String;
import hei.devweb.traderz.entities.Campus;
import hei.devweb.traderz.entities.Capteur;
import hei.devweb.traderz.entities.Salle;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.*;
import org.json.simple.JSONArray;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import java.lang.String;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/Prive/Salle", "/Admin/Salle"})
public class SalleServlet extends PrivateServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        String id_selected_salle = req.getParameter("id_selected_salle");
        Salle selected_salle = SalleManager.getInstance().GetSalleFromId(Integer.parseInt(id_selected_salle));
        Campus campus_with_the_selected_salle = CampusManager.getInstance().GetCampusFromSalleId(Integer.parseInt(id_selected_salle));
        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        Boolean is_salle_favori = FavoriManager.getInstance().IsSalleFavori(user.getIdUser(), selected_salle.getId());
        String ajouter_ou_supprimer;
        if(is_salle_favori){
            ajouter_ou_supprimer = "Supprimer des Favoris";
        }else {
            ajouter_ou_supprimer = "Ajouter aux Favoris";
        }


//        System.out.println(array_of_temperature);

        context.setVariable("useronline", user );
        context.setVariable("selected_salle", selected_salle );
        context.setVariable("campus_with_the_selected_salle", campus_with_the_selected_salle);
        context.setVariable("ajouter_ou_supprimer", ajouter_ou_supprimer);

        Date date = new Date();
        String today= new SimpleDateFormat("yyyy-MM-dd").format(date);

        String debut_graph_date = ((debut_graph_date = req.getParameter("debut_graph_date")) != null) ? debut_graph_date : today;
        String debut_graph_heure = ((debut_graph_heure = req.getParameter("debut_graph_heure")) != null) ? debut_graph_heure : "08:00";

        String fin_graph_date = ((fin_graph_date = req.getParameter("fin_graph_date")) != null) ? fin_graph_date : today;
        String fin_graph_heure = ((fin_graph_heure = req.getParameter("fin_graph_heure")) != null) ? fin_graph_heure : "20:00";
        for (Salle salle : campus_with_the_selected_salle.getList_salles()){
            System.out.println(salle.getBatiment_name());
        }
        System.out.println();
        if (user.getRole().equals("admin")){
//            On récupère les informations des dates de début et de fin pour les graphs

//            Date date = new Date();
//            String today= new SimpleDateFormat("yyyy-MM-dd").format(date);
//
//            String debut_graph_date = ((debut_graph_date = req.getParameter("debut_graph_date")) != null) ? debut_graph_date : today;
//            String debut_graph_heure = ((debut_graph_heure = req.getParameter("debut_graph_heure")) != null) ? debut_graph_heure : "08:00";
//
//            String fin_graph_date = ((fin_graph_date = req.getParameter("fin_graph_date")) != null) ? fin_graph_date : today;
//            String fin_graph_heure = ((fin_graph_heure = req.getParameter("fin_graph_heure")) != null) ? fin_graph_heure : "20:00";

            context.setVariable("debut_graph_date", debut_graph_date);
            context.setVariable("debut_graph_heure", debut_graph_heure);
            context.setVariable("fin_graph_date", fin_graph_date);
            context.setVariable("fin_graph_heure", fin_graph_heure);

            JSONArray array_of_all_sensors_info = CapteurManager.getInstance().GetAllSensorsInfoWithDates(debut_graph_date, debut_graph_heure, fin_graph_date, fin_graph_heure, selected_salle.getId());
            context.setVariable("array_of_all_sensors_info", array_of_all_sensors_info);

            Capteur actual_sensors_info = CapteurManager.getInstance().GetActualAllSensorsInfo();
            context.setVariable("actual_sensors_info", actual_sensors_info);

            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Admin/salle", context, resp.getWriter());
        }else{

            context.setVariable("debut_graph_date", debut_graph_date);
            context.setVariable("debut_graph_heure", debut_graph_heure);
            context.setVariable("fin_graph_date", fin_graph_date);
            context.setVariable("fin_graph_heure", fin_graph_heure);

            JSONArray array_of_temperature = SalleManager.getInstance().GetTemperature(debut_graph_date, debut_graph_heure, fin_graph_date, fin_graph_heure, selected_salle.getId());
            context.setVariable("array_of_temperature", array_of_temperature);

            Capteur actual_humidity_and_temperature = CapteurManager.getInstance().GetActualTempAndHumidity();
            context.setVariable("actual_humidity_and_temperature", actual_humidity_and_temperature);

            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/salle", context, resp.getWriter());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());

        String switch_favori = req.getParameter("switch_favori");
        String salle_id = req.getParameter("salle_id");

//        Get element from the searching bar
        String text_searched = ((text_searched = req.getParameter("text_searched")) != null) ? text_searched : "";

        System.out.println(text_searched);
        if(!text_searched.equals("")){
            req.getSession().setAttribute("user_searched_from_salle", text_searched);
            if(user.getRole().equals("admin")){
                resp.sendRedirect("/traderz_war/Admin/Home");
            }else {
                resp.sendRedirect("/traderz_war/Prive/Home");
            }
        }else{

        System.out.println("SWITCH_FAVORI " + switch_favori);
        if(switch_favori.equals("del")){
            FavoriManager.getInstance().DeleteFavoriFromSalleIdAndUserId(Integer.parseInt(salle_id), user.getIdUser());
            System.out.println("We just DELETED the FAVORI for the room " + salle_id);

        }else {
            FavoriManager.getInstance().AddFavori(user.getIdUser(), 1, Integer.parseInt(salle_id));
            System.out.println("We just ADDED the FAVORI for the room " + salle_id);

        }
//        AlerteManager.getInstance().AddAlerte(datetime, message, user.getIdUser(), Integer.parseInt(salle_id), titre);

        if(user.getRole().equals("admin")){
            resp.sendRedirect("/traderz_war/Admin/Salle?id_selected_salle=" + salle_id);
        }else {
            resp.sendRedirect("/traderz_war/Prive/Salle?id_selected_salle=" + salle_id);
        }
    }
    }
}
