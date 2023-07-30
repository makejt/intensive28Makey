package org.example.makey.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.makey.exception.ProductNotFoundException;
import org.example.makey.model.Product;
import org.example.makey.repository.ProductRepository;
import org.example.makey.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> showAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> optionalProduct = repository.findById(id);
        return optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product don't exist by ID = " + id));
    }
    @Override
    @Transactional
    public void saveProduct(Product product) {
        repository.save(product);
    }

    @Override
    @Transactional
    public void deleteProductByID(Integer id) {
        repository.deleteById(id);
    }
}
