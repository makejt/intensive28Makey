package org.example.makey.controller;

import lombok.RequiredArgsConstructor;
import org.example.makey.model.Customer;
import org.example.makey.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public List<Customer> showAllCustomers() {
       return service.showAllCustomers();
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> showCustomerById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerById(id));
    }
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer newCustomer) throws ServerException {
        if (newCustomer == null) {
            throw new ServerException("Customer is null");
        }
        service.saveCustomer(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
    @PutMapping(value = "/customer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        service.update(id, customer);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        service.deleteCustomerByID(id);
    }
}