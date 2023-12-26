package Database_Connection;
import java.sql.*;

public class ConnectionProvider implements AutoCloseable {
    
    public Connection connection;
    public Statement statement;

    public ConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbms", "root", "123456");
            statement = connection.createStatement();
        } catch(Exception e) {
            // Log the error instead of just printing the stack trace
            e.printStackTrace();
        }
    }

    // Add a method to close resources
    public void closeResources() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Log the error instead of just printing the stack trace
            e.printStackTrace();
        }
    }

    // Implement AutoCloseable to use try-with-resources
    @Override
    public void close() {
        closeResources();
    }
}
