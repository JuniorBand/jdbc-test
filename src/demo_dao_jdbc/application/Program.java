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


        SellerDao sellerDao = DaoFactory.createSellerDao(new SellerDaoJDBC(DB.getConnection()));

        System.out.println("=== TEST 1: seller findById ===");
        Seller sel = sellerDao.findById(3);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(new DepartmentDaoJDBC());

        System.out.println(sel);

    }
}