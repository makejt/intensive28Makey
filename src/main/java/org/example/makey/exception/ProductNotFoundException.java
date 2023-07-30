package org.example.makey.exception;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
