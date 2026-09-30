package servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlloUsernameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext context = new InitialContext();
            this.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/MioDBPool");
        } catch (NamingException e) {
            throw new ServletException("Impossibile trovare la Connection Pool", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
        String username = json.get("username").getAsString();

        String sql = "SELECT COUNT(*) FROM utente WHERE username = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    response.getWriter().write(
                        "{ \"username_esiste\": true, \"esito\": true }"
                    );
                } else {
                    response.getWriter().write(
                        "{ \"username_esiste\": false, \"esito\": true }"
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(
                "{ \"username_esiste\": false, \"esito\": false, \"error\": \"Errore database\" }"
            );
        }
    }
}
