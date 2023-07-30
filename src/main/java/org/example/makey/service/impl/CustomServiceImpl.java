package org.example.makey.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.makey.exception.CustomerNotFoundException;
import org.example.makey.model.Customer;
import org.example.makey.repository.CustomerRepository;
import org.example.makey.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository repository;

    @Override
    public List<Customer> showAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        return optionalCustomer.orElseThrow(() -> new CustomerNotFoundException("Customer don't exist by ID = " + id));
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public void update(Integer id, Customer customer) {
        Customer customer1 = getCustomerById(id);
        customer1.setINN(customer.getINN());
        customer1.setBrandName(customer.getBrandName());
        customer1.setLegalForm(customer.getLegalForm());
        customer1.setManager(customer.getManager());
    }

    @Override
    @Transactional
    public void deleteCustomerByID(Integer id) {
        repository.deleteById(id);
    }
}
