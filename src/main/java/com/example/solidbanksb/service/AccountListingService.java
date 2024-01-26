package com.example.solidbanksb.service;


import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.Account.AccountWithdraw;

import java.util.List;

public interface AccountListingService {
    Account getClientAccount(String accountId);
    AccountWithdraw getClientWithdrawAccount(String accountId);
    List<Account> getClientAccounts(String clientId);
    List<Account> getClientAccountsByType(String clientId, AccountType accountType);

    AccountWithdraw getClientDepositAccount(String clientId, String accountId);
}

