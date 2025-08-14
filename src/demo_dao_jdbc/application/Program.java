package demo_dao_jdbc.application;

import demo_dao_jdbc.db.DB;
import demo_dao_jdbc.model.dao.DaoFactory;
import demo_dao_jdbc.model.dao.DepartmentDao;
import demo_dao_jdbc.model.dao.SellerDao;
import demo_dao_jdbc.model.dao.impl.DepartmentDaoJDBC;
import demo_dao_jdbc.model.dao.impl.SellerDaoJDBC;
import demo_dao_jdbc.model.entities.Department;
import demo_dao_jdbc.model.entities.Seller;

import java.sql.Connection;
import java.util.Date;


public class Program {

    public static void main(String[] args) {

//        Department dep = new Department(1, "Books");
//        Seller sel = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, dep);

        SellerDao sellerDao = DaoFactory.createSellerDao(new SellerDaoJDBC(DB.getConnection()));

        Seller sel = sellerDao.findById(3);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(new DepartmentDaoJDBC());

        System.out.println(sel);


    }
}