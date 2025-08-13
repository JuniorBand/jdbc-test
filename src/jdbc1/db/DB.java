package jdbc1.db;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException("Error connecting to the database: " + e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException("Error closing the database connection: " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db-test.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException("Failed to load database properties: " + e.getMessage());
        }
        
    }

}
