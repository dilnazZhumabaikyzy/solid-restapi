package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.model.Account.*;
import com.example.solidbanksb.service.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCreationServiceImpl implements AccountCreationService {
    @Autowired
    AccountDao accountDao;
    @Override
    public void create(String accountId, AccountType accountType,String clientId) {
        System.out.println("Account creation...");
        switch (accountType){
//            case CHECKING -> accountDao.createNewAccount(new CheckingAccount(accountType, clientId, 0.0, true));
//            case FIXED -> accountDao.createNewAccount(new FixedAccount(accountType, clientId,   0.0, false));
//            case SAVING -> accountDao.createNewAccount(new SavingAccount(accountType, clientId,   0.0, true));
            default -> {
                Account acc = new Account();
                acc.setId(accountId);
                acc.setAccountType(accountType.getType());
                acc.setBalance(0.0);
                acc.setWithdrawAllowed(true);
                acc.setClientId(clientId);
                accountDao.createNewAccount(acc);
            }
        }
    }
}
