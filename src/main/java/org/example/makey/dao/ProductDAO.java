package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.History;
import org.example.makey.model.Product;
import org.example.makey.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;

public class ProductDAO implements AbstractDAO<Product>{

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void add(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setCreated(new Timestamp(System.currentTimeMillis()));
        product.setHistory(history);
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void update(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        product.setHistory(history);
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Product.class, id));
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public Product getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session.get(Product.class, id);
    }
    @Override
    public List<Product> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Product> products = (List<Product>)session.createQuery("From Product").list();
        return products;
    }
}