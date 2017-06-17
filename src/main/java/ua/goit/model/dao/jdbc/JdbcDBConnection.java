package ua.goit.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDBConnection {

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/pm";
        // -Dusername=XXX -Dpassword=YYYY
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

}
