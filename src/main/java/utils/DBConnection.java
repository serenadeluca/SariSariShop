package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        Context initContext = new InitialContext();
        Context envContext  = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/SariSariPool");
        return ds.getConnection();
    }

	
}
