package org.example.makey.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.makey.exception.ManagerNotFoundException;
import org.example.makey.model.Manager;
import org.example.makey.repository.ManagerRepository;
import org.example.makey.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private final ManagerRepository repository;

    @Override
    public List<Manager> showAllManagers() {
        return repository.findAll();
    }

    @Override
    public Manager getManagerById(Integer id) {
        Optional<Manager> optionalManager = repository.findById(id);
        return optionalManager.orElseThrow(() -> new ManagerNotFoundException("Manager don't exist by ID = " + id));
    }

    @Override
    @Transactional
    public void saveManager(Manager manager) {
        repository.save(manager);
    }

    @Override
    @Transactional
    public void deleteManagerByID(Integer id) {
        repository.deleteById(id);
    }
}