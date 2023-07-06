package org.example.makey.service;

import org.example.makey.dao.CustomerDAO;
import org.example.makey.model.Customer;
import java.util.Optional;
import java.util.Set;

public class CustomServiceImpl implements CustomerService{

    private CustomerDAO dao = new CustomerDAO();
    @Override
    public Set<Customer> showAllCustomers() {
        return dao.getAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Customer customer;
        Optional<Customer> optionalCustomer = Optional.ofNullable(dao.getById(id));
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        } else {
            throw new RuntimeException("Customer don't exist by ID = " + id);
        }
        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        dao.add(customer);
    }
    @Override
    public void deleteCustomerByID(Integer id) {
        dao.delete(id);
    }
}
