package org.example.makey.controller;

import lombok.RequiredArgsConstructor;
import org.example.makey.model.Manager;
import org.example.makey.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService service;
    @GetMapping
    public List<Manager> showAllManagers() {
        return service.showAllManagers();
    }
    @GetMapping("/manager/{id}")
    public Manager showManagerById(@PathVariable Integer id) {
        return service.getManagerById(id);
    }

    @PostMapping(value = "/manager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manager> saveProduct(@RequestBody Manager newManager) throws ServerException {
        if (newManager == null) {
            throw new ServerException("Manager is null");
        }
        service.saveManager(newManager);
        return new ResponseEntity<>(newManager, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteManager(@PathVariable Integer id) {
        service.deleteManagerByID(id);
    }
}