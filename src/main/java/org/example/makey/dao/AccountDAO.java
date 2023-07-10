package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.Account;
import org.example.makey.model.Customer;
import org.example.makey.model.History;
import org.example.makey.util.HibernateUtil;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.util.List;

public class AccountDAO implements AbstractDAO<Account>{

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void add(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setCreated(new Timestamp(System.currentTimeMillis()));
        account.setHistory(history);
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void update(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        account.setHistory(history);
        session.update(account);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Account.class, id));
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public Account getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session.get(Account.class, id);
    }
    @Override
    public List<Account> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Account> accounts = session.createQuery("FROM Account a join fetch a.customer", Account.class).getResultList();
        return accounts;
    }
}