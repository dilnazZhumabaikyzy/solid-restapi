package com.example.solidbanksb.service;


import com.example.solidbanksb.model.Account.Account;

public interface AccountWithdrawService {
    boolean withdraw(double amount, String account_id) throws Exception;
}
