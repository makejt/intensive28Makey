package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.Account;
import org.example.makey.util.DBUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
public class AccountDAO implements AbstractDAO<Account>{

    private static final Logger logger = LogManager.getLogger();
    private CustomerDAO dao = new CustomerDAO();
    @Override
    public boolean add(Account account) {
        String sql = "INSERT INTO account (number, currency, rest, customer_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setString(1, account.getNumber());
            pst.setInt(2, account.getCurrency());
            pst.setDouble(3, account.getRest());
            pst.setInt(4, account.getCustomer().getId());

            if (pst.executeUpdate() == 1) {
                logger.info("Account was added in DB successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
          logger.error("Account wasn't added in DB");
        return false;
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE account SET number = ? , currency = ? , rest = ? , customer_id = ? WHERE id = ?";

        try (Connection connection1 = DBUtils.getConnection();
             PreparedStatement pst = connection1.prepareStatement(sql);) {

            pst.setString(1, account.getNumber());
            pst.setInt(2, account.getCurrency());
            pst.setDouble(3, account.getRest());
            pst.setInt(4, account.getCustomer().getId());
            pst.setInt(5, account.getId());

            if (pst.executeUpdate() == 1) {
                logger.info("Account was updated in DB successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        logger.error("Account wasn't updated in DB");
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM account WHERE account_id = ?";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                logger.info("Account was deleted");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.error("Account wasn't deleted");
        return false;
    }

    @Override
    public Account getById(int id) {
        Account account = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Connection connection = DBUtils.getConnection();

        String sql = "SELECT * FROM account WHERE account_id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setId(id);
                account.setNumber(rs.getString("number"));
                account.setCurrency(Integer.parseInt(rs.getString("currency")));
                account.setRest(Double.parseDouble(rs.getString("rest")));
                account.setCustomer(dao.getById(rs.getInt("customer_id")));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            DBUtils.close(connection);
        }
        return account;
    }
    @Override
    public Set<Account> getAll() {
        Set<Account> accounts = new HashSet<Account>();
        Account account = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String sql = "SELECT * FROM account";

        try {
            connection = DBUtils.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("account_id"));
                account.setNumber(rs.getString("number"));
                account.setCurrency(Integer.parseInt(rs.getString("currency")));
                account.setRest(Double.parseDouble(rs.getString("rest")));
                account.setCustomer(dao.getById(rs.getInt("customer_id")));
                accounts.add(account);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.close(connection);
        }
        return accounts;
    }
}
