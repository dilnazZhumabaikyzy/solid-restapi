package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.model.Account.*;
import com.example.solidbanksb.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void create(AccountRequest accountRequest) throws AccountCreationException {
        System.out.println("Account creation...");
                String accountId = generateAccountId(accountRequest.getClientId());
                Account acc = Account.builder().
                        accountType(accountRequest.getAccountType()).
                        balance(accountRequest.getBalance()).
                        withdrawAllowed(accountRequest.isWithdrawAllowed()).
                        clientId(accountRequest.getClientId()).
                        id(accountId).build();

               accountDao.createNewAccount(acc);
    }
    public  String generateAccountId(String clientId) {
        return String.format("%03d%06d", Integer.valueOf(clientId), getLastAccountId());
    }

    private int getLastAccountId() {
        String lastAccountId = entityManager.createQuery("SELECT MAX(SUBSTR(a.id, 5)) FROM Account a", String.class)
                .getSingleResult();
        return (Integer.parseInt(lastAccountId) + 1);
    }
    @Override
    public Account getAccount(String accountId) throws Exception {
        return accountDao.getClientAccount(accountId);
    }

    @Override
    public void deleteAccount(String accountId) {
        accountDao.deleteAccount(accountId);
    }
}
