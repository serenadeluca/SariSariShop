package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class AreaPersonaleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AreaPersonaleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Controllo login corretto
        if (session == null || session.getAttribute("id_utente") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Utente loggato → vai alla pagina personale
        request.getRequestDispatcher("/pages/area_personale.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
