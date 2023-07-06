package org.example.makey.service;

import org.example.makey.dao.ProductDAO;
import org.example.makey.model.Product;

import java.util.Optional;
import java.util.Set;

public class ProductServiceImpl implements ProductService{
    private ProductDAO dao = new ProductDAO();
    @Override
    public Set<Product> showAllProducts() {
        return dao.getAll();
    }

    @Override
    public Product getProductById(Integer id) {
        Product product;
        Optional<Product> optionalProduct = Optional.ofNullable(dao.getById(id));
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw new RuntimeException("Product don't exist by ID = " + id);
        }
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        dao.add(product);
    }

    @Override
    public void deleteProductByID(Integer id) {
        dao.delete(id);
    }
}
