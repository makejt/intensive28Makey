package org.example.makey.service;

import org.example.makey.dao.ManagerDAO;
import org.example.makey.model.Manager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ManagerServiceImpl implements ManagerService{
    private ManagerDAO dao = new ManagerDAO();

    @Override
    public Set<Manager> showAllManagers() {
       return dao.getAll();
    }
    @Override
    public Manager getManagerById(Integer id) {
        Manager manager;
        Optional<Manager> optionalManager = Optional.ofNullable(dao.getById(id));
        if (optionalManager.isPresent()) {
            manager = optionalManager.get();
        } else {
            throw new RuntimeException("Manager don't exist by ID = " + id);
        }
        return manager;
    }
    @Override
    public void saveManager(Manager manager) {
        dao.add(manager);
    }
    @Override
    public void deleteManagerByID(Integer id) {
        dao.delete(id);
    }
}