package org.example.makey.service;

import org.example.makey.model.Product;

import java.util.Set;

public interface ProductService {
    Set<Product> showAllProducts();

    Product getProductById(Integer id);

    void saveProduct(Product product);

    void deleteProductByID(Integer id);
}
