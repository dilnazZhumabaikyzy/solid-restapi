package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.Account.AccountWithdraw;
import com.example.solidbanksb.service.AccountListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountListingServiceImpl implements AccountListingService {
    AccountDao accountDao;

    @Autowired
    public AccountListingServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account getClientAccount(String accountId) throws Exception {
       return accountDao.getClientAccount(accountId);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount( String accountId) {
        return null;
    }

    @Override
    public List<Account> getClientAccounts(String clientId) {
        return accountDao.getClientAccounts(clientId);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientId, AccountType accountType) {
        return accountDao.getClientAccountsByType(clientId, accountType);
    }

    @Override
    public AccountWithdraw getClientDepositAccount(String clientId, String accountId) {
        return null;
    }

    @Override
    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }


}
