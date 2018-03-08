package hei.devweb.traderz.servlets;

import hei.devweb.traderz.dao.impl.TransactionDaoImpl;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CotationManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Prive/acheterCotation")
public class AcheterCotationServlet extends PrivateServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String companyId = req.getParameter("id");
        Cotation cotation = CotationManager.getInstance().CreateCotationFromId(Integer.parseInt(companyId));
        context.setVariable("cotation", cotation);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("achat", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String volume = req.getParameter("nbAction");
        Double volumeAction = Double.parseDouble(volume);
        String userconnected = (String) req.getSession().getAttribute("user");
        String companyId = req.getParameter("id");
        Cotation cotation = CotationManager.getInstance().CreateCotationFromId(Integer.parseInt(companyId));
        User user = UserManager.getInstance().CreateUserFromPseudo(userconnected);
        TransactionDaoImpl transactionDao = new TransactionDaoImpl();

        Double valeur = new UserDaoImpl().CreateValeur(userconnected);
        Double liquidite = new UserDaoImpl().CreateLiquidite(userconnected);
        Double valeurTransac = cotation.getPrix()*volumeAction;

        String errorMessage = null;
        try{
            if (volumeAction!=0){
                transactionDao.Acheter(user, cotation, volumeAction);
                new UserDaoImpl().Debiter(liquidite, valeur, valeurTransac, userconnected);
                resp.sendRedirect("/Prive/cotations");
            }
        }catch (Exception e ) {
            errorMessage = e.getMessage();
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cotation", cotation);

        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("achat", context, resp.getWriter());
    }

}
