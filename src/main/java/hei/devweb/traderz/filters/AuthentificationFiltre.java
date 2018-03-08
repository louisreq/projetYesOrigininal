package hei.devweb.traderz.filters;


import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Filtre de d'authentification d'utilisateurs
// Un utilisateurs peut ouvrir les pages concernées par le filtre que s'il est connecté
// Autrement il sera redirigé vers la page d'accueil

@WebFilter(filterName = "AuthentificationFiltre")
public class AuthentificationFiltre implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String identifiant = (String) httpRequest.getSession().getAttribute("user");
        if (identifiant == null || "".equals(identifiant)){
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            httpResponse.sendRedirect("../pageAccueil");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
