package com.example.solidbanksb.service;


import com.example.solidbanksb.model.Account.AccountType;


public interface AccountCreationService {
    void create(AccountType accountType, long bankId, String clientId, String accountId);
}
