package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.service.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountDepositServiceImpl implements AccountDepositService {

    @Autowired
    private AccountDao accountDao;


    @Override
    public boolean deposit(double amount, String accountId) throws Exception {
        Account account = accountDao.getClientAccount(accountId);
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        accountDao.updateAccount(account);
        return true;

    }
}
