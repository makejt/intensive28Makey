package org.example.makey.service;

import org.example.makey.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    List<Customer> showAllCustomers();

    Customer getCustomerById(Integer id);

    void saveCustomer(Customer customer);

    void deleteCustomerByID(Integer id);
}
