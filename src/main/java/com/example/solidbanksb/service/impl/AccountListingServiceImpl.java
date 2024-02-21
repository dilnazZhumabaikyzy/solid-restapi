package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.user.User;
import com.example.solidbanksb.service.AccountListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<Account> getClientAccounts() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int clientId = 0;
        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof User user) {
                clientId = user.getId();
            }
        }

        return accountDao.getClientAccounts(clientId);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientId, AccountType accountType) {
        return accountDao.getClientAccountsByType(clientId, accountType);
    }

    @Override
    public List<Account> getAccounts() {
        return accountDao.getAccounts();
    }


}
