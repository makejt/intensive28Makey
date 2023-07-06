package org.example.makey.service;

import org.example.makey.model.Customer;

import java.util.Set;

public interface CustomerService {
    Set<Customer> showAllCustomers();

    Customer getCustomerById(Integer id);

    void saveCustomer(Customer customer);

    void deleteCustomerByID(Integer id);
}
