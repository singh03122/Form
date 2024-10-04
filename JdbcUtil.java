package in.Rohit.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

    // Method to establish a database connection
    public static Connection getDbConnection() throws SQLException {
        Connection connection = null;
        try {
            // Load the database driver (optional)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection (replace 'yourDatabase' with actual DB name)
            String url = "jdbc:mysql://localhost:3306/rohitdb";
            String username = "root";
            String password = "XC99@rohit";

            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // You can log this in a production environment
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return connection;
    }

    // Method to close resources
    public static void closeResources(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
