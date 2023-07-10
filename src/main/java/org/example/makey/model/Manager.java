package org.example.makey.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "email")
    private String email;
    @Embedded
    private History history;
    public Manager(String name, String lastName, String telephone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
    }
}