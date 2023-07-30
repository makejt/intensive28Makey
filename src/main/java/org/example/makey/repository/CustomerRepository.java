package org.example.makey.repository;

import org.example.makey.model.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @EntityGraph(attributePaths = {"products", "manager"})
    List<Customer> findAll();
}