package com.example.solidbanksb.service;

import com.example.solidbanksb.model.Account.AccountRepository;
import com.example.solidbanksb.model.Account.AccountType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankCore {

    @PersistenceContext
    private EntityManager entityManager;
    AccountCreationService accountCreationService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    public BankCore(AccountCreationService accountCreationService) {
        this.accountCreationService = accountCreationService;
    }


//    public String generateAccountId(String clientId) {
//        return String.format("%03d%06d", Integer.valueOf(clientId), getLastAccountId());
//    }
//
//    private int getLastAccountId() {
//        double lastAccountId = entityManager.createQuery("SELECT MAX(CAST(SUBSTR(a.id, 5) AS DECIMAL)) FROM Account a", Double.class)
//                .getSingleResult();
//        return ((int) lastAccountId + 1);
//    }

    public void createNewAccount( AccountType accountType, String clientId){
        String accountId = generateAccountId(clientId);

        accountCreationService.create(accountId, accountType, clientId);
    }

    public String generateAccountId(String clientId) {
        return String.format("%03d%06d", Integer.valueOf(clientId), getLastAccountId());
    }

    private int getLastAccountId() {
        String lastAccountId = entityManager.createQuery("SELECT MAX(SUBSTR(a.id, 5)) FROM Account a", String.class)
                .getSingleResult();
        return (Integer.parseInt(lastAccountId) + 1);
    }

}
