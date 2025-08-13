package jdbc4.application;

import jdbc4.db.DB;

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
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = testConnection();

        try {
            st = conn.prepareStatement(
                "UPDATE sellers " +
                "SET Salary = Salary + ?" +
                "WHERE " +
                    "DepartmentId = ?");

            st.setDouble(1, 200.0);
            st.setInt(2, 4);

//            st = conn.prepareStatement(
//                    "insert into departments (Name) values ('D1'), ('D2')",
//                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();


            if(rowsAffected > 0) {
                System.out.println("Rows affected: " + rowsAffected);
            }
            else {
                System.out.println("No rows affected!");
            }

        } //catch (ParseException e) {
//            e.printStackTrace();
        //}
        catch (SQLException e) {
            e.printStackTrace();
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