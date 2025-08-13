package jdbc3.application;

import jdbc3.db.DB;

import java.sql.*;
import java.text.ParseException;
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
//            st = conn.prepareStatement(
//                "INSERT INTO sellers " +
//                "(Name, Email, BirthDate, Salary, DepartmentId) " +
//                "VALUES " +
//                "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//
//            st.setString(1, "Carl Purple");
//            st.setString(2, "carl@gmail.com");
//            st.setDate(3, new java.sql.Date(sdf.parse("22/07/1985").getTime()));
//            st.setDouble(4, 3000.0);
//            st.setInt(5, 4);

            st = conn.prepareStatement(
                    "insert into departments (Name) values ('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);



            int rowsAffected = st.executeUpdate();


            if(rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
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