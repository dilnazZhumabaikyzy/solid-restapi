package com.example.solidbanksb.model.Account;


import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.CreateAccountOperationUI;
import com.example.solidbanksb.service.AccountListingService;
import com.example.solidbanksb.service.BankCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountBasicCli {
    BankCore bankCore;
    AccountListingService accountListingService;
    @Autowired
    public AccountBasicCli(BankCore bankCore, AccountListingService accountListingService) {
        this.bankCore = bankCore;
        this.accountListingService = accountListingService;
    }

    public void createAccountRequest(AccountType accountType, String clientId){
        bankCore.createNewAccount(accountType, clientId);
    }

    public void getAccounts(String clientId){
        List<Account> accountsList = accountListingService.getClientAccounts(clientId);
        if(accountsList.isEmpty()){
            System.out.println("No accounts found for client " + clientId);
        } else {
            System.out.println("Accounts for client: " + clientId);
           for(Account account: accountsList){
               System.out.println(account);
               System.out.println("-----------------------------------");
           }
        }
    }
}