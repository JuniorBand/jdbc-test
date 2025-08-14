package demo_dao_jdbc.model.dao.impl;

import demo_dao_jdbc.db.DB;
import demo_dao_jdbc.db.DbException;
import demo_dao_jdbc.model.dao.DepartmentDao;
import demo_dao_jdbc.model.dao.SellerDao;
import demo_dao_jdbc.model.entities.Department;
import demo_dao_jdbc.model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller sel) {

    }

    @Override
    public void update(Seller sel) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT sellers.*,departments.Name as DepName " +
                            "FROM sellers INNER JOIN departments " +
                            "ON sellers.DepartmentId = departments.id " +
                            "WHERE sellers.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                // Assuming Department has a constructor that takes id and name
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));

                // Assuming Seller has a constructor that takes all necessary fields
                Seller sel = new Seller();

                sel.setId(rs.getInt("Id"));
                sel.setName(rs.getString("Name"));
                sel.setEmail(rs.getString("Email"));
                sel.setBirthDate(rs.getDate("BirthDate"));
                sel.setBaseSalary(rs.getDouble("Salary"));

                sel.setDepartment(dep);

                return sel;
            } else {
                return null; // No seller found with the given id
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findAll() {

        return null;
    }



}
