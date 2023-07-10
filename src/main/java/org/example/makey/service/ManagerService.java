package org.example.makey.service;

import org.example.makey.model.Manager;

import java.util.List;
import java.util.Set;

public interface ManagerService {

    List<Manager> showAllManagers();

    Manager getManagerById(Integer id);

    void saveManager(Manager manager);

    void deleteManagerByID(Integer id);

}
