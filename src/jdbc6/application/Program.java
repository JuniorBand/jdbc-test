package jdbc6.application;

import jdbc6.db.DB;
import jdbc6.db.DbException;
import jdbc6.db.DbIntegrityException;

import java.sql.*;
import java.text.SimpleDateFormat;

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Statement st = null;
        ResultSet rs = null;
        Connection conn = testConnection();

        try {
            st = conn.createStatement();

            conn.setAutoCommit(false);

            int rows1 = st.executeUpdate("UPDATE sellers SET Salary = 2090 WHERE DepartmentId = 1");

//            int x = 1;
//            if(x < 2) {
//                throw new SQLException("Fake error for testing purposes");
//            }

            int rows2 = st.executeUpdate("UPDATE sellers SET Salary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.printf("Rows affected by first update: %d%n", rows1);
            System.out.printf("Rows affected by second update: %d%n", rows2);

        } //catch (ParseException e) {
//            e.printStackTrace();
        //}
        catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back due to an error: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to roll back due to: " + ex.getMessage());
            }
        } finally {
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