package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.Manager;
import org.example.makey.util.DBUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ManagerDAO implements AbstractDAO<Manager> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean add(Manager manager) {

        String sql = "INSERT INTO manager (name, last_name, telephone, email) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setString(1, manager.getName());
            pst.setString(2, manager.getLastName());
            pst.setInt(3, manager.getTelephone());
            pst.setString(4, manager.getEmail());

            if (pst.executeUpdate() == 1) {
                logger.info("Manager was added in DB successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        logger.error("Manager wasn't added in DB");
        return false;
    }

    @Override
    public boolean update(Manager manager) {

        String sql = "UPDATE manager SET name = ? , last_name = ? , telephone = ? , email = ? WHERE id = ?";

        try (Connection connection1 = DBUtils.getConnection();
             PreparedStatement pst = connection1.prepareStatement(sql);) {

            pst.setString(1, manager.getName());
            pst.setString(2, manager.getLastName());
            pst.setInt(3, manager.getTelephone());
            pst.setString(4, manager.getEmail());
            pst.setInt(5, manager.getId());

            if (pst.executeUpdate() == 1) {
                logger.info("Manager was updated in DB successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        logger.error("Manager wasn't updated in DB");
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM manager WHERE manager_id = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Manager was deleted");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.error("Manager wasn't deleted");
        return false;
    }

    @Override
    public Manager getById(int id) {
        Manager manager = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Connection connection = DBUtils.getConnection();

        String sql = "SELECT * FROM manager WHERE manager_id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                manager = new Manager();
                manager.setId(id);
                manager.setName(rs.getString("name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setTelephone(rs.getInt("telephone"));
                manager.setEmail(rs.getString("email"));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            DBUtils.close(connection);
        }
        return manager;
    }
    @Override
    public Set<Manager> getAll() {
        Set<Manager> managers = new HashSet<Manager>();
        Manager manager = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String sql = "SELECT * FROM manager";

        try {
            connection = DBUtils.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                manager = new Manager();
                manager.setId(rs.getInt("manager_id"));
                manager.setName(rs.getString("name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setTelephone(rs.getInt("telephone"));
                manager.setEmail(rs.getString("email"));
                managers.add(manager);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.close(connection);
        }
        return managers;
    }
}
