package org.example.makey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;
    @Column(name = "number")
    private String number;
    @Column(name = "currency")
    private int currency;
    @Column(name = "rest")
    private double rest;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @Embedded
    private History history;

    public Account(String number, int currency, double rest) {
        this.number = number;
        this.currency = currency;
        this.rest = rest;
    }
}