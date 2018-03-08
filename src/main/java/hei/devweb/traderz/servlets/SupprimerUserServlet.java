package hei.devweb.traderz.servlets;

import hei.devweb.traderz.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Prive/supprimerUser")
public class SupprimerUserServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");
        UserManager.getInstance().supprimerUser(userconnected);
        req.getSession().removeAttribute("user");
        resp.sendRedirect("pageAccueil");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
