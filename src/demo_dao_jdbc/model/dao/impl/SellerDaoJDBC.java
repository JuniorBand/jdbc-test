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
import java.util.*;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller sel) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("INSERT INTO sellers" +
                    "(Name, Email, BirthDate, Salary, DepartmentId)" +
                    "VALUES" +
                    "(?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, sel.getName());
            st.setString(2, sel.getEmail());
            st.setDate(3, new java.sql.Date(sel.getBirthDate().getTime()));
            st.setDouble(4, sel.getBaseSalary());
            st.setInt(5, sel.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();

                if(rs.next()) {
                    sel.setId(rs.getInt(1)); // Assuming Seller has a setId method
                }
            } else {
                throw new DbException("Unexpected error! No rows affected.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(Seller sel) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE sellers " +
                    "SET Name = ?, Email = ?, BirthDate = ?, Salary = ?, DepartmentId = ? " +
                    "WHERE Id = ?");

            st.setString(1, sel.getName());
            st.setString(2, sel.getEmail());
            st.setDate(3, new java.sql.Date(sel.getBirthDate().getTime()));
            st.setDouble(4, sel.getBaseSalary());
            st.setInt(5, sel.getDepartment().getId());
            st.setInt(6, sel.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

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
                Department dep = instantiateDepartment(rs);

                // Assuming Seller has a constructor that takes all necessary fields
                Seller sel = instantiateSeller(rs, dep);

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

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT sellers.*,departments.Name as DepName " +
                            "FROM sellers INNER JOIN departments " +
                            "ON sellers.DepartmentId = departments.Id " +
                            "ORDER BY Name");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();


            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller sel = instantiateSeller(rs, dep);

                list.add(sel);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT sellers.*,departments.Name as DepName " +
                            "FROM sellers INNER JOIN departments " +
                            "ON sellers.DepartmentId = departments.Id " +
                            "WHERE DepartmentId = ? " +
                            "ORDER BY Name");

            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();


            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller sel = instantiateSeller(rs, dep);

                list.add(sel);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        // Assuming Department has a constructor that takes id and name
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller sel = new Seller();
        // Assuming Seller has a constructor that takes id, name, email, birthDate, salary
        sel.setId(rs.getInt("Id"));
        sel.setName(rs.getString("Name"));
        sel.setEmail(rs.getString("Email"));
        sel.setBirthDate(rs.getDate("BirthDate"));
        sel.setBaseSalary(rs.getDouble("Salary"));
        sel.setDepartment(dep);
        return sel;
    }

}
