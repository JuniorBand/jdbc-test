package jdbc5.application;

import jdbc5.db.DB;
import jdbc5.db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                "DELETE FROM departments " +
                    "WHERE " +
                    "id IN (?, ?)");

            st.setInt(1, 1);
            st.setInt(2, 2);

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
            throw new DbIntegrityException(e.getMessage());
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