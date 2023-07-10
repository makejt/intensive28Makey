package org.example.makey.service;

import org.example.makey.model.Account;

import java.util.List;
import java.util.Set;

public interface AccountService {

    List<Account> showAllAccounts();

    Account getAccountById(Integer id);

    void saveAccount(Account account);

    void deleteAccountByID(Integer id);

}
