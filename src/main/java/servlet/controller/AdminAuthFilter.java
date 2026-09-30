package servlet.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("admin") != null);

        String uri = request.getRequestURI();

        // 1. Lascia passare file CSS, JS e risorse statiche
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".ico")) {
            chain.doFilter(req, res);
            return;
        }

        // 2. Rotte pubbliche dell'admin (Login e Registrazione)
        boolean isPublicPage = 
                uri.endsWith("/pages/admin/login") ||
                uri.endsWith("/pages/admin/admin-login.jsp") ||
                uri.endsWith("/admin/registrazione") ||
                uri.endsWith("/pages/admin/admin-registrazione.jsp");

        // 3. Controllo accessi
        if (loggedIn || isPublicPage) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/admin/admin-login.jsp");
        }
    }

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}