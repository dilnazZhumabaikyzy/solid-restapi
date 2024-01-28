package com.example.solidbanksb.DAO;


import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;

import java.util.List;


public interface AccountDao {
    List<Account> getClientAccounts(String clientId);
    void createNewAccount(Account account) throws AccountCreationException;
    void updateAccount(Account account) throws Exception;

    List<Account> getClientAccountsByType(String clientId, AccountType accountType);
    Account getClientAccount(String accountId) throws Exception;

    List<Account> getAccounts();

    void deleteAccount(String accountId);
}
