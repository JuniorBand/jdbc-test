package jdbc2.application;

import jdbc2.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Program {

    private static Connection testConnection() {
        Connection conn = DB.getConnection();
        if (conn != null) {
            System.out.println("Database connection established successfully.");
        } else {
            System.out.println("Failed to establish database connection.");
        }
        return conn;
    }

    public static void main(String[] args) {

        Statement st = null;
        ResultSet rs = null;
        Connection conn = testConnection();

        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from departments");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ", " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                DB.closeStatement(st);
                DB.closeResultSet(rs);
                DB.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}