package org.example.makey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
//@NamedEntityGraph(
//        name = "WithProductsAndAccounts",
//        attributeNodes = {
//                @NamedAttributeNode("products"),
//                @NamedAttributeNode("accounts")
//        }
//)
//@NamedEntityGraph(
//        name = "WithAccounts",
//        attributeNodes = {
//
//                @NamedAttributeNode("accounts")
//        }
//)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "legal_form")
    private String legalForm;
    @Column(name = "INN")
    private int INN;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "customer_product",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
    )
    private List<Product> products;
    @Embedded
    private History history;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    @JsonIgnore
    private List<Account> accounts;

    public Customer(String brandName, String legalForm, int INN) {
        this.brandName = brandName;
        this.legalForm = legalForm;
        this.INN = INN;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", legalForm='" + legalForm + '\'' +
                ", INN=" + INN +
                '}';
    }
}