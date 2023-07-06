package org.example.makey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private int id;
    private String name;
    private String lastName;
    private int telephone;
    private String email;
    public Manager(String name, String lastName, int telephone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
    }
}