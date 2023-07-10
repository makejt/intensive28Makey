package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.Customer;
import org.example.makey.model.History;
import org.example.makey.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;


public class CustomerDAO implements AbstractDAO<Customer>{
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void add(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setCreated(new Timestamp(System.currentTimeMillis()));
        customer.setHistory(history);
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void update(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setCreated(new Timestamp(System.currentTimeMillis()));
        customer.setHistory(history);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Customer.class, id));
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public Customer getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session.get(Customer.class, id);
    }
    @Override
    public List<Customer> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Customer> customers = session.createQuery("FROM Customer c join fetch c.accounts", Customer.class).getResultList();
        return customers;
    }
}