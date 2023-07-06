package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.Customer;
import org.example.makey.util.DBUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
public class CustomerDAO implements AbstractDAO<Customer>{
    private ManagerDAO dao = new ManagerDAO();
    private static final Logger logger = LogManager.getLogger();
    @Override
    public boolean add(Customer customer) {
        String sql = "INSERT INTO customer (brand_name, legal_form, INN, manager_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setString(1, customer.getBrandName());
            pst.setString(2, customer.getLegalForm());
            pst.setInt(3, customer.getINN());
            pst.setInt(4, customer.getManager().getId());

            if (pst.executeUpdate() == 1) {
                logger.info("Customer was added in DB successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        logger.error("Customer wasn't added in DB");
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        String sql = "UPDATE customer SET brand_name = ? , legal_form = ? , INN = ? , manager_id = ? " +
                "WHERE id = ?";

        try (Connection connection1 = DBUtils.getConnection();
             PreparedStatement pst = connection1.prepareStatement(sql);) {

            pst.setString(1, customer.getBrandName());
            pst.setString(2, customer.getLegalForm());
            pst.setInt(3, customer.getINN());
            pst.setInt(4, customer.getManager().getId());
            pst.setInt(5, customer.getId());

            if (pst.executeUpdate() == 1) {
                logger.info("Customer was updated in DB successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        logger.error("Customer wasn't updated in DB");
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Customer was deleted");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.error("Customer wasn't deleted");
        return false;
    }

    @Override
    public Customer getById(int id) {
        Customer customer = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Connection connection = DBUtils.getConnection();

        String sql = "SELECT id, brand_name, legal_form, INN, manager_id FROM customer WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(id);
                customer.setBrandName(rs.getString("brand_name"));
                customer.setLegalForm(rs.getString("legal_form"));
                customer.setINN(rs.getInt("INN"));
                customer.setManager(dao.getById(rs.getInt("manager_id")));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            DBUtils.close(connection);
        }
        return customer;
    }
    @Override
    public Set<Customer> getAll() {
        Set<Customer> customers= new HashSet<Customer>();
        Customer customer = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String sql = "SELECT id, brand_name, legal_form, INN, manager_id FROM customer";

        try {
            connection = DBUtils.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setBrandName(rs.getString("brand_name"));
                customer.setLegalForm(rs.getString("legal_form"));
                customer.setINN(rs.getInt("INN"));
                customer.setManager(dao.getById(rs.getInt("manager_id")));
                customers.add(customer);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.close(connection);
        }
        return customers;
    }
}