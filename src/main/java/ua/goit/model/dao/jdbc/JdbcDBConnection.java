package ua.goit.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JdbcDBConnection {


    Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/pm";
        // -Dusername=XXX -Dpassword=YYYY
        //String username = System.getProperty("username");
        //String password = System.getProperty("password");
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(url, username, password);
    }

}
