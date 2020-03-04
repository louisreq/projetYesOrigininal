package hei.devweb.traderz.servlets;

import hei.devweb.traderz.entities.*;
import hei.devweb.traderz.managers.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/Admin/Capteur"})
public class CapteurServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        String message_information_apres_ajout_rasberry = (String) req.getSession().getAttribute("message_information_apres_ajout_rasberry");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());


        List<Rasberry> liste_rasberry = RasberryManager.getInstance().GetAllRasberrySalle();
        Map<Integer, String> mapping_id_salle_name = SalleManager.getInstance().GetAllSallesMapedWithIdAndCampusName();
        Map<Integer, String> mapping_id_salle_etage = SalleManager.getInstance().GetAllSallesMapedWithIdAndEtage();
        Map<Integer, String> mapping_id_and_name_rasberry = RasberryManager.getInstance().GetAllRasberry();

        List<Campus> liste_campus = CampusManager.getInstance().GetListOfCampus() ;

        for (Campus campus : liste_campus){
            campus.setList_salles( CampusManager.getInstance().GetListOfSalleWithCampusId(campus.getId()) );
        }



        //        Créer une liste qui contient les noms de tous les Rasberry existants
        List<String> liste_rasberry_name = new ArrayList<>();
        for(Rasberry rasberry : liste_rasberry){
            liste_rasberry_name.add(rasberry.getNom_rasberry());
            System.out.println("On vient d'ajouter ce capteur à la liste globale : " + rasberry.getNom_rasberry());
        }
        List<String> liste_rasberry_non_localises = new ArrayList<>();
        List<String> rasberry_all = new ArrayList<>();
        for(Integer key : mapping_id_and_name_rasberry.keySet()){
            System.out.println("Ce Capteur est localisé : " + mapping_id_and_name_rasberry.get(key));
            rasberry_all.add(mapping_id_and_name_rasberry.get(key));

            if (!liste_rasberry_name.contains(mapping_id_and_name_rasberry.get(key))){
                liste_rasberry_non_localises.add(mapping_id_and_name_rasberry.get(key));
                System.out.println("On vient d'ajouter ce Capteur à la liste : " + mapping_id_and_name_rasberry.get(key) + " (celle des capteurs non localisés)");
            }

        }
        System.out.println("Cela nous retourne la liste suivante : " + rasberry_all + " (Capteurs localisés)");



        context.setVariable("useronline", user );
        context.setVariable("liste_rasberry", liste_rasberry);
        context.setVariable("mapping_id_salle_name", mapping_id_salle_name);
        context.setVariable("mapping_id_salle_etage", mapping_id_salle_etage);
        context.setVariable("liste_campus", liste_campus);
        context.setVariable("mapping_id_and_name_rasberry", mapping_id_and_name_rasberry);
        context.setVariable("liste_rasberry_name", liste_rasberry_name);
        context.setVariable("liste_rasberry_non_localises", liste_rasberry_non_localises);
        context.setVariable("message_information_apres_ajout_rasberry", message_information_apres_ajout_rasberry);


        if (user.getRole().equals("admin")){
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

            templateEngine.process("/WEB-INF/Templates/Admin/Capteurs", context, resp.getWriter());
        }else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("/WEB-INF/Templates/Prive/Capteurs", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_connected_email = (String) req.getSession().getAttribute("user_connected_email");
        User user = UserManager.getInstance().CreateUserFromEmail(user_connected_email); // Nous permet d'acceder à toutes les informations de l'utilisateur connecté en session

        WebContext context = new WebContext(req, resp, req.getServletContext());
        String form_submitted = req.getParameter("Valider_formulaire");

        //        Get element from the searching bar
        String text_searched = ((text_searched = req.getParameter("text_searched")) != null) ? text_searched : "";

        System.out.println(text_searched);
        if(!text_searched.equals("")){
            req.getSession().setAttribute("user_searched_from_salle", text_searched);
            resp.sendRedirect("/traderz_war/Admin/Home");

        }else{
            System.out.println("\n\nVoici le formulaire retourné \t ----> \t " + form_submitted);
            if(form_submitted.equals("Ajouter une localisation du capteur")){
                // Modal Ajout Localisation à un Capteur
                String id_salle_selected_add_localisation = (req.getParameter("salle_add_localisation") != null ? req.getParameter("salle_add_localisation") : "");
                String name_rasberry_add_localisation = (req.getParameter("rasberry_add_localisation") != null ? req.getParameter("rasberry_add_localisation") : "");
                String emplacement_add_localisation = (req.getParameter("emplacement_add_localisation") != null ? req.getParameter("emplacement_add_localisation") : "");
                Date date = new Date();
                Timestamp datetime = new java.sql.Timestamp(date.getTime());

                Rasberry rasberry_location_added = new Rasberry(null,
                        name_rasberry_add_localisation,
                        Integer.parseInt(id_salle_selected_add_localisation),
                        emplacement_add_localisation,
                        datetime,
                        null);
                RasberryManager.getInstance().AddRasberrySalle(rasberry_location_added);
                System.out.println("We just added an now location to the rasberry : " + name_rasberry_add_localisation);
            }else if(form_submitted.equals("Valider les modifications de localisation")){
//            Modal Modification d'une localisation
                String name_rasberry_edit_localisation = req.getParameter("nom_rasberry_selected_hidden");
                String id_salle_selected_edit_localisation = req.getParameter("salle_selected_edit_localisation");
                String emplacement_salle_selected = req.getParameter("emplacement_salle_selected");
                Date date = new Date();
                Timestamp datetime = new java.sql.Timestamp(date.getTime());

                Rasberry rasberry_location_edited = new Rasberry(null,
                        name_rasberry_edit_localisation,
                        Integer.parseInt(id_salle_selected_edit_localisation),
                        emplacement_salle_selected,
                        datetime,
                        datetime);
//            1) On ajoute une date de fin à l'ancienne localisation du Capteur
                RasberryManager.getInstance().SetDateFinRasberrySalle(rasberry_location_edited);
                System.out.println("We juste ended the old localisation for the Rasberry : " + name_rasberry_edit_localisation);
//            2) On ajoute la nouvelle localisation
                RasberryManager.getInstance().AddRasberrySalle(rasberry_location_edited);
                System.out.println("We just seted a new localisation for the rasberry : " + name_rasberry_edit_localisation);
            }else if(form_submitted.equals("Retirer le capteur de sa salle")){
//            Retirer la localisation du Capteur
                String nom_rasberry_remove_localisation = req.getParameter("nom_rasberry_remove_localisation");
                String id_salle_remove_localisation = req.getParameter("id_salle_remove_localisation");
                String emplacement_remove_localisation = req.getParameter("emplacement_remove_localisation");
                Date date = new Date();
                Timestamp datetime = new java.sql.Timestamp(date.getTime());

                Rasberry rasberry_location_remove = new Rasberry(null,
                        nom_rasberry_remove_localisation,
                        Integer.parseInt(id_salle_remove_localisation),
                        emplacement_remove_localisation,
                        datetime,
                        datetime);
                RasberryManager.getInstance().SetDateFinRasberrySalle(rasberry_location_remove);
                System.out.println("We juste ended the localisation for the Rasberry : " + nom_rasberry_remove_localisation);

            }else if(form_submitted.equals("Supprimer ce capteur Definitivement ?")){
                String nom_rasberry_remove = req.getParameter("nom_rasberry_remove");
                Date date = new Date();
                Timestamp datetime = new java.sql.Timestamp(date.getTime());

                Rasberry rasberry_remove = new Rasberry(null,
                        nom_rasberry_remove,
                        null,
                        null,
                        datetime,
                        datetime);
                RasberryManager.getInstance().SetDateFinRasberrySalle(rasberry_remove);
                RasberryManager.getInstance().DeleteRasberry(rasberry_remove);
            }else if(form_submitted.equals("Ajouter un nouveau capteur ?")){
//            1) On regarde si le nom du capteur est déjà existant
                String nom_add_rasberry = req.getParameter("nom_add_rasberry");
                Boolean is_name_already_taken = RasberryManager.getInstance().IsCapteurNameAlreadyTaken(nom_add_rasberry);
                String message_information_apres_ajout_rasberry;
                if(!is_name_already_taken){
//                2) Si le nom du capteur est disponible, on crée alors ce capteur
                    RasberryManager.getInstance().AddRasberry(nom_add_rasberry);
                    message_information_apres_ajout_rasberry = "Le Capteur >" + nom_add_rasberry + "< a bien été ajouté";
                }else {
                    message_information_apres_ajout_rasberry = "Le Capteur >" + nom_add_rasberry + "< n'a pas pu être ajouté, ce nom est déjà pris.";
                }
                req.getSession().setAttribute("message_information_apres_ajout_rasberry", message_information_apres_ajout_rasberry);
            }else if(form_submitted.equals("Valider le nouveau nom du capteur")){
                String new_name_rasberry = req.getParameter("nouveau_nom_rasberry");
                String old_name_rasberry = req.getParameter("nom_rasberry");
                RasberryManager.getInstance().EditRasberry(old_name_rasberry, new_name_rasberry);
                RasberryManager.getInstance().EditRasberrySalleName(old_name_rasberry, new_name_rasberry);

                System.out.println("We juste renamed the Rasberry : " + old_name_rasberry + " To -> " + new_name_rasberry);
            }

            if(user.getRole().equals("admin")){
                resp.sendRedirect("/traderz_war/Admin/Capteur");
            }else {
                resp.sendRedirect("/traderz_war/Prive/Capteur");
            }
        }

    }
}
