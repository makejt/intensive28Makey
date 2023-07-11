package org.example.makey.dao;

import org.example.makey.model.Manager;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ManagerDAOTest {
    private static final ManagerDAO dao = new ManagerDAO();

    @Test
    void add() {
        List<Manager> managers1 = dao.getAll();
        Manager manager = new Manager("Татьяна2", "Макей", "3881543", "TMakei@mail.ru");
        dao.add(manager);
        List<Manager> managers2 = dao.getAll();
        assertTrue(managers1.size() == managers2.size() - 1);
    }

    @Test
    void update() {
        Manager manager = dao.getById(2);
        manager.setName("Светлана");
        dao.update(manager);
        manager = dao.getById(2);
        assertTrue(manager.getName().equals("Светлана"));
    }

    @Test
    void delete() {
        List<Manager> managers1 = dao.getAll();
        dao.delete(5);
        List<Manager> managers2 = dao.getAll();
        assertTrue(managers1.size() - 1 == managers2.size());
    }
}
