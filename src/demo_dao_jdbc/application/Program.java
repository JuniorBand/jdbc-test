package demo_dao_jdbc.application;

import demo_dao_jdbc.application.model.entities.Department;
import demo_dao_jdbc.db.DB;
import demo_dao_jdbc.db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Department department = new Department(1, "Books");

    }
}