package hei.devweb.traderz.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Prive/Deconnexion", "/Admin/Deconnexion"})
public class DeconnexionServlet extends PrivateServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user_connected_email");
        resp.sendRedirect("/traderz_war/PageConnexion");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user_connected_email", null);
        resp.sendRedirect("/traderz_war/PageConnexion");
    }
}
