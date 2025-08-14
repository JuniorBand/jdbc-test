package demo_dao_jdbc.application;

import demo_dao_jdbc.db.DB;
import demo_dao_jdbc.model.dao.DaoFactory;
import demo_dao_jdbc.model.dao.DepartmentDao;
import demo_dao_jdbc.model.dao.impl.DepartmentDaoJDBC;
import demo_dao_jdbc.model.entities.Department;
import demo_dao_jdbc.model.entities.Seller;


import java.util.List;
import java.util.Scanner;

public class ProgramDepartment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao(new DepartmentDaoJDBC(DB.getConnection()));

        System.out.println("=== TEST 1: department findById ===");
        Department sel = departmentDao.findById(3);
        System.out.println(sel);

        System.out.println("=== TEST 2: department findSellers ===");
        Department dep = new Department(4, null);
        List<Seller> list = departmentDao.findSellers(dep);

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 3: department findAll ===");
        List<Department> depList = departmentDao.findAll();

        for (Department obj : depList) {
            System.out.println(obj);
        }

        System.out.println("=== TEST 4: department insert ===");
        Department newDepartment = new Department(null, "Porn");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());
        departmentDao.findById(newDepartment.getId());
        System.out.println(newDepartment);

        System.out.println("=== TEST 5: department update ===");
        Department departmentUpdate = departmentDao.findById(1);
        departmentUpdate.setName("Sex Toys");
        departmentDao.update(departmentUpdate);
        System.out.println("Update completed!");
        System.out.println(departmentUpdate);

        System.out.println("=== TEST 6: department delete ===");
        System.out.println("Insert a valid id to delete:");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        depList = departmentDao.findAll();

        for (Department obj : depList) {
            System.out.println(obj);
        }

        sc.close();
    }
}
