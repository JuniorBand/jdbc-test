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
import java.util.List;


public class Program {

    public static void main(String[] args) {


        SellerDao sellerDao = DaoFactory.createSellerDao(new SellerDaoJDBC(DB.getConnection()));

        System.out.println("=== TEST 1: seller findById ===");
        Seller sel = sellerDao.findById(3);
        System.out.println(sel);

        System.out.println("=== TEST 2: seller findByDepartment ===");
        Department dep = new Department(4, null);
        List<Seller> list = sellerDao.findByDepartment(dep);

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 3: seller findAll ===");
        list = sellerDao.findAll();

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail, com", new Date(), 4000.0, dep);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        sellerDao.findById(newSeller.getId());
        System.out.println(newSeller);


    }
}