package jdbc1.application;

import jdbc1.db.DB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        if (conn != null) {
            System.out.println("Database connection established successfully.");
        } else {
            System.out.println("Failed to establish database connection.");
        }
        DB.closeConnection();
    }
}