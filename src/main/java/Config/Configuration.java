package Config;

import java.sql.*;

public class Configuration {

    private static final String URL = "jdbc:postgresql://localhost:5432/skypro";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    public static Connection connectToDB() throws SQLException {

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
