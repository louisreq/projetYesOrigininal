package hei.devweb.traderz.servlets;


import hei.devweb.traderz.dao.impl.CotationDaoImpl;
import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.CotationManager;
import hei.devweb.traderz.managers.UserManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import sun.net.httpserver.HttpServerImpl;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/Prive/cotations")
public class CotationsServlet extends PrivateServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userconnected = (String) req.getSession().getAttribute("user");
        CotationDaoImpl cotationDao = new CotationDaoImpl();
        cotationDao.CleanCotations();
        String symbols[] = new String[]{"BN.PA","SAN.PA","ORA.PA","OR.PA","CAP.PA","FP.PA","KER.PA","SGO.PA","EN.PA","LHN.PA","ENGI.PA","FR.PA","SW.PA","ATO.PA","UG.PA","AIR.PA","DG.PA","SU.PA","VIV.PA","AI.PA","BNP.PA","VIE.PA","ACA.PA","CA.PA","AC.PA","LR.PA"};
// ML.PA  "GLE.PA" "EI.PA" MC.PA"}
        for (int i = 0; i < symbols.length; i++) { // Boucle qui va récupérer les noms des cotations du tableaux de string symbols
            cotationDao.InitCotation(YahooFinance.get(symbols[i]));
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.removeVariable("list");
        List<Cotation> cotationListAD = CotationManager.getInstance().listCotationAD();
        User user = UserManager.getInstance().CreateUserFromPseudo(userconnected);
        context.setVariable("useronline", user );

        context.setVariable("list", cotationListAD);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("cotations", context, resp.getWriter());

    }
}
