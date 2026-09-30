package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/testError")
public class TestErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String codeParam = req.getParameter("code");

        if (codeParam != null) {
            int code = Integer.parseInt(codeParam);
            resp.sendError(code);
            return;
        }

        resp.getWriter().write("Usa ?code=404, 500, 403, 400 per testare gli errori.");
    }
}
