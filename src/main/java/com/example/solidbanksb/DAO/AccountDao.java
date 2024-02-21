package com.example.solidbanksb.DAO;


import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountDTO;
import com.example.solidbanksb.model.Account.AccountType;

import java.util.List;


public interface AccountDao {
    List<AccountDTO> getClientAccounts(Integer clientId);
    void createNewAccount(Account account) throws AccountCreationException;
    void updateAccount(Account account) throws Exception;

    List<Account> getClientAccountsByType(String clientId, AccountType accountType);
    Account getClientAccount(String accountId) throws Exception;

    List<AccountDTO> getAccounts();

    void deleteAccount(String accountId);
}
