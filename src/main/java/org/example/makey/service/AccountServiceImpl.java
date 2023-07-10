package org.example.makey.service;

import org.example.makey.dao.AccountDAO;
import org.example.makey.model.Account;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService{

    private AccountDAO dao = new AccountDAO();
    @Override
    public List<Account> showAllAccounts() {
        return dao.getAll();
    }

    @Override
    public Account getAccountById(Integer id) {
        Account account;
        Optional<Account> optionalAccount = Optional.ofNullable(dao.getById(id));
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new RuntimeException("Account don't exist by ID = " + id);
        }
        return account;
    }

    @Override
    public void saveAccount(Account account) {
        dao.add(account);
    }

    @Override
    public void deleteAccountByID(Integer id) {
        dao.delete(id);
    }
}
