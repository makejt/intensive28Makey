package org.example.makey.exception;

public class AccountNotFoundException extends ResourceNotFoundException {

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

}