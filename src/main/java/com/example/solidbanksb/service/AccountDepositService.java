package com.example.solidbanksb.service;

import com.example.solidbanksb.model.Account.Account;

public interface AccountDepositService {
    boolean deposit(double amount, String account_id) throws Exception;
}
