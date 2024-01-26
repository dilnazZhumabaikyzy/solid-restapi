package com.example.solidbanksb.model.TransactionDeposit;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.WithdrawDepositOperationCLIUI;
import com.example.solidbanksb.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Component
public class TransactionDepositCLI{
    @Autowired
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    AccountListingService accountListingService;
    @Autowired
    TransactionDeposit transactionDeposit;

    public void depositMoney(String accountNumber, double amount) {
        Account account= accountListingService.getClientAccount(accountNumber);

        transactionDeposit.execute(account, amount);
    }
}
