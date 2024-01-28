package com.example.solidbanksb.service;


import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;

import java.util.List;

public interface AccountListingService {
    Account getClientAccount(String accountId) throws Exception;
    List<Account> getClientAccounts(String clientId);
    List<Account> getClientAccountsByType(String clientId, AccountType accountType);

    List<Account> getAccounts();

}

