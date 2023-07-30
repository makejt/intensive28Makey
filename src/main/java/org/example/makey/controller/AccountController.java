package org.example.makey.controller;

import lombok.RequiredArgsConstructor;
import org.example.makey.model.Account;
import org.example.makey.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @GetMapping
    public List<Account> showAllAccounts() {
        return service.showAllAccounts();
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> showAccountById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }

    @PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> saveAccount(@RequestBody Account newAccount) throws ServerException {
        if (newAccount == null) {
            throw new ServerException("Product is null");
        }
        service.saveAccount(newAccount);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Integer id) {
        service.deleteAccountByID(id);
    }
}