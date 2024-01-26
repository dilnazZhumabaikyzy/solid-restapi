package com.example.solidbanksb.DAO;


import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.Account.AccountWithdraw;

import java.util.List;


public interface AccountDao {
    List<Account> getClientAccounts(String clientId);
    void createNewAccount(Account account);
    void updateAccount(Account account) throws Exception;

    List<Account> getClientAccountsByType(String clientId, AccountType accountType);
    AccountWithdraw getClientWithdrawAccount(String clientId, String accountId);
    Account getClientAccount(String accountId);
}
