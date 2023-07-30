package org.example.makey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {AccountNotFoundException.class, CustomerNotFoundException.class,
            ManagerNotFoundException.class, ProductNotFoundException.class})
    @ResponseStatus (value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException exception) {
        Response response = new Response(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}