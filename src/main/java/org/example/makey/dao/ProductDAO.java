package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.Product;
import org.example.makey.util.DBUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ProductDAO implements AbstractDAO<Product>{

    private static final Logger logger = LogManager.getLogger();
    @Override
    public boolean add(Product product) {
        String sql = "INSERT INTO product type = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setString(1, product.getType());

            if (pst.executeUpdate() == 1) {
                logger.info("Product was added in DB successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        logger.error("Product wasn't added in DB");
        return false;
    }

    @Override
    public boolean update(Product product) {
        String sql = "UPDATE product SET type = ?";

        try (Connection connection1 = DBUtils.getConnection();
             PreparedStatement pst = connection1.prepareStatement(sql);) {

            pst.setString(1, product.getType());

            if (pst.executeUpdate() == 1) {
                logger.info("Product was updated in DB successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        logger.error("Product wasn't updated in DB");
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Product was deleted");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.error("Product wasn't deleted");
        return false;
    }

    @Override
    public Product getById(int id) {
        Product product = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Connection connection = DBUtils.getConnection();

        String sql = "SELECT * FROM product WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(id);
                product.setType(rs.getString("type"));

            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            DBUtils.close(connection);
        }
        return product;
    }
    @Override
    public Set<Product> getAll() {
        Set<Product> products = new HashSet<Product>();
        Product product = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String sql = "SELECT * FROM product";

        try {
            connection = DBUtils.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setType(rs.getString("type"));
                products.add(product);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.close(connection);
        }
        return products;
    }
}