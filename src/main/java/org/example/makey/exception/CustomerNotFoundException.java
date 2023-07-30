package org.example.makey.exception;

public class CustomerNotFoundException extends ResourceNotFoundException {

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

}