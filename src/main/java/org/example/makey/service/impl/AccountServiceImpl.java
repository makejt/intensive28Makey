package org.example.makey.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.makey.exception.AccountNotFoundException;
import org.example.makey.model.Account;
import org.example.makey.repository.AccountRepository;
import org.example.makey.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private final AccountRepository repository;

    @Override
    public List<Account> showAllAccounts() {
        return repository.findAll();
    }
    @Override
    public Account getAccountById(Integer id) {
        Optional<Account> optionalAccount = repository.findById(id);
        return optionalAccount.orElseThrow(() -> new AccountNotFoundException("Account don't exist by ID = " + id));
    }
    @Override
    @Transactional
    public void saveAccount(Account account) {
        repository.save(account);
    }
    @Override
    @Transactional
    public void deleteAccountByID(Integer id) {
        repository.deleteById(id);
    }
}
