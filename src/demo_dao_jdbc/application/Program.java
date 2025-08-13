package demo_dao_jdbc.application;

import demo_dao_jdbc.model.entities.Department;
import demo_dao_jdbc.model.entities.Seller;

import java.util.Date;


public class Program {

    public static void main(String[] args) {

        Department dep = new Department(1, "Books");
        Seller sel = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, dep);
        System.out.println(dep + "\n"+ sel);

    }
}