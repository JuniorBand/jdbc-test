package demo_dao_jdbc.model.dao;


public class DaoFactory {

    public static SellerDao createSellerDao(SellerDao sellerDao) {
        return sellerDao;
    }

    public static DepartmentDao createDepartmentDao(DepartmentDao departmentDao) {
        return departmentDao;
    }


}
