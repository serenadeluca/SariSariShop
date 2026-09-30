package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        // 🔥 FIX: getRequestURI() funziona SEMPRE, getServletPath() NO
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        String path = uri.substring(ctx.length());   // es: /SariSariShop/index.jsp → /index.jsp
        
     // SE È UNA PAGINA ADMIN → LASCIA STARE, CI PENSA AdminAuthFilter
        if (path.startsWith("/pages/admin/")) {
            chain.doFilter(req, res);
            return;
        }

        boolean isPublic =
                path.equals("/") ||
                path.equals("/index.jsp") ||
                path.equals("/index.html") ||

                path.equals("/login") ||
                path.equals("/pages/login.jsp") ||

                path.equals("/registrazione") ||
                path.equals("/pages/registrazione.jsp") ||

                path.equals("/catalogo") ||
                path.equals("/dettaglioProdotto/") ||
                path.startsWith("/cercaSuggerimenti") ||
                path.equals("/controllo_email") ||
                path.equals("/confermaOrdine") ||
                path.equals("/confermaOrdine/") ||
                path.equals("/favicon2.ico") ||

                // ⭐ ADMIN — TUTTO IL PANNELLO DEVE PASSARE ⭐
                path.startsWith("/pages/admin/login") ||
                path.startsWith("/pages/admin/admin-login.jsp") ||
                path.startsWith("/pages/admin/") ||
                path.equals("/admin/registrazione") ||  // 👈 AGGIUNGI QUESTA
                path.endsWith("admin-registrazione.jsp") ||// 👈 AGGIUNGI QUESTA
                path.startsWith("/css") ||
                path.startsWith("/js") ||
                path.startsWith("/images") ||
                path.startsWith("/img") ||
                path.startsWith("/fonts");





        if (isPublic) {
            chain.doFilter(req, res);
            return;
        }

        // -------------------------------
        // 2️⃣ Controllo autenticazione
        // -------------------------------
        boolean loggedIn = (session != null && session.getAttribute("id_utente") != null);

        if (!loggedIn) {

            // Riconoscimento AJAX
            String requestedWith = request.getHeader("X-Requested-With");
            boolean isAjax = "XMLHttpRequest".equals(requestedWith);

            if (isAjax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"success\": false, \"error\": \"login_required\"}");
            } else {
                response.sendRedirect(request.getContextPath() + "/pages/login.jsp?error=login_required");
            }

            return;
        }

        // -------------------------------
        // 3️⃣ Utente autenticato → continua
        // -------------------------------
        chain.doFilter(req, res);
    }
}
