package org.example.makey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String brandName;
    private String legalForm;
    private int INN;
    private Manager manager;

    private Set<Product> products;

    public Customer(String brandName, String legalForm, int INN, Manager manager, Set<Product> products) {
        this.brandName = brandName;
        this.legalForm = legalForm;
        this.INN = INN;
        this.manager = manager;
        this.products = products;
    }
}