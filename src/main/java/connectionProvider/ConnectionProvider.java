package connectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "admin";
    private static final String url = "jdbc:mysql://localhost:3306/computerconfigurator";

    public static Connection conect() {
        return ConnectionProvider.getInstance();

    }

    public static Connection getInstance() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
