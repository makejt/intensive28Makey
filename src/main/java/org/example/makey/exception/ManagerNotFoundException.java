package org.example.makey.exception;

public class ManagerNotFoundException extends ResourceNotFoundException {

    public ManagerNotFoundException() {
        super();
    }

    public ManagerNotFoundException(String message) {
        super(message);
    }

}