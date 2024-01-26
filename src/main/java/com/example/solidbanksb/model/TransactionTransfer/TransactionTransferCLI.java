package com.example.solidbanksb.model.TransactionTransfer;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.TransferOperationCLIUI;
import com.example.solidbanksb.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@AllArgsConstructor
@Component
public class TransactionTransferCLI {

    @Autowired
    TransferOperationCLIUI transferOperationCLIUI;
    @Autowired
    AccountListingService accountListingService;
    @Autowired
    TransactionTransfer transactionTransfer;


    public void transfer(String fromAccountNumber, String toAccountNumber, double transferAmount) throws Exception {

        Account fromAccount = accountListingService.getClientAccount(fromAccountNumber);
        Account toAccount = accountListingService.getClientAccount(toAccountNumber);

        transactionTransfer.execute(fromAccount, toAccount, transferAmount);

    }
}
