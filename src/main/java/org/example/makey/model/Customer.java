package org.example.makey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(
        name = "WithManagerAndAccount",
        attributeNodes = {
                @NamedAttributeNode("manager"),
                @NamedAttributeNode("accounts")
        }
)


@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "customer")
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

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "customer_product",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
    )
    private List<Product> products;
    @Embedded
    private History history;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
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