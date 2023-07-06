package org.example.makey.service;

import org.example.makey.model.Manager;

import java.util.Set;

public interface ManagerService {

    Set<Manager> showAllManagers();

    Manager getManagerById(Integer id);

    void saveManager(Manager manager);

    void deleteManagerByID(Integer id);

}
