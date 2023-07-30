package org.example.makey.controller;

import lombok.RequiredArgsConstructor;
import org.example.makey.model.Product;
import org.example.makey.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    @GetMapping
    public List<Product> showAllProducts() {
        return service.showAllProducts();
    }
    @GetMapping("/product/{id}")
    public Product showProductById(@PathVariable Integer id) {
        return service.getProductById(id);
    }
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody Product newProduct) throws ServerException {
        if (newProduct == null) {
            throw new ServerException("Product is null");
        }
        service.saveProduct(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        service.deleteProductByID(id);
    }
}