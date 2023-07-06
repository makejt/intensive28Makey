package org.example.makey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String number;
    private int currency;
    private double rest;
    private Customer customer;
    public Account(String number, int currency, double rest, Customer customer) {
        this.number = number;
        this.currency = currency;
        this.rest = rest;
        this.customer = customer;
    }
}