package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == null) {
            statusCode = 500;
        }

        request.setAttribute("statusCode", statusCode);

        request.getRequestDispatcher("/pages/errorPage.jsp").forward(request, response);
    }
}
