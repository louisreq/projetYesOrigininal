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

@WebServlet("/Prive/portefeuille")
public class PortefeuilleServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        User user = UserManager.getInstance().CreateUserFromPseudo(userconnected);
        List<Transaction> transactions = new TransactionDaoImpl().listTransacByUser(userconnected);
        context.setVariable("transactions",transactions);
        context.setVariable("useronline", user );
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("portefeuille", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTransac = req.getParameter("collectTransac");
        String check1 = req.getParameter("check");
        Integer check2 = Integer.parseInt(check1);
        Integer idtransaction = Integer.parseInt(idTransac);

        TransactionDaoImpl transactionDao = new TransactionDaoImpl();
        Transaction transaction = TransactionManager.getInstance().CreateTransacFromId(idtransaction);
        String userconnected = (String) req.getSession().getAttribute("user");
        Double liquidite = new UserDaoImpl().CreateLiquidite(userconnected);
        Double gain = transaction.getGain();
        Double valeurachat = transaction.getTransacPrix();

        String errorMessage = null;

        try {
            if(check2>0) {

                transactionDao.DeleteTransac(idtransaction);
                transactionDao.Revendre(transaction);
                new UserDaoImpl().Crediter(liquidite, gain, valeurachat, userconnected);
                resp.sendRedirect("/Prive/portefeuille");
            }}catch (Exception e ) {
            errorMessage = e.getMessage();
        }

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("transaction", transaction);

        context.setVariable("errorMessage", errorMessage);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("portefeuille", context, resp.getWriter());
    }
}
