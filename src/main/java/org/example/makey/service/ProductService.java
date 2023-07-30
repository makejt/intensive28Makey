package org.example.makey.service;


import org.example.makey.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> showAllProducts();

    Product getProductById(Integer id);

    void saveProduct(Product product);

    void deleteProductByID(Integer id);
}
