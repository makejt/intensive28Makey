package org.example.makey.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.makey.model.History;
import org.example.makey.model.Manager;
import org.example.makey.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.List;


public class ManagerDAO implements AbstractDAO<Manager> {
    private static final Logger logger = LogManager.getLogger(ManagerDAO.class);
    @Override
    public void add(Manager manager) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setCreated(new Timestamp(System.currentTimeMillis()));
        manager.setHistory(history);
        session.save(manager);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void update(Manager manager) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        History history = new History();
        history.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        manager.setHistory(history);
        session.update(manager);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Manager.class, id));
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public Manager getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session.get(Manager.class, id);
    }
    @Override
    public List<Manager> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        logger.info("beginTransaction");
        List<Manager> managers = (List<Manager>)session.createQuery("From Manager").list();
        logger.info(managers);
        return managers;
    }
}