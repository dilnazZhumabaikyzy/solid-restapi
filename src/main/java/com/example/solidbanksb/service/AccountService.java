package com.example.solidbanksb.service;


import com.example.solidbanksb.ResponseMessage;
import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountRequest;
import org.springframework.http.ResponseEntity;


public interface AccountService {
    void create(AccountRequest accountRequest) throws AccountCreationException;
    Account getAccount(String account_id) throws Exception;

    void deleteAccount(String accountId);
}
