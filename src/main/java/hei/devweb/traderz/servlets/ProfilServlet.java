package hei.devweb.traderz.servlets;

import hei.devweb.traderz.dao.impl.TransactionDaoImpl;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.Transaction;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.TransactionManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Prive/profil")
public class ProfilServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##"); // Utilisé pour donner un double avec seulement 2 chiffres

        WebContext context = new WebContext(req, resp, req.getServletContext());

        User user = UserManager.getInstance().CreateUserFromPseudo(userconnected);
        List<Transaction> HistoTransac = new TransactionDaoImpl().HistoriqueTransacByUser(userconnected);
        context.setVariable("transactions", HistoTransac);
        context.setVariable("useronline", user );
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("profil", context, resp.getWriter());
    }
}

