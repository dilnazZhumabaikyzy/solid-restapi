package com.example.solidbanksb.model.TransactionWithdraw;


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
public class TransactionWithdrawCLI {

        @Autowired
        WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
        @Autowired
        AccountListingService accountListingService;
        @Autowired
        TransactionWithdraw transactionWithdraw;

        public void withdrawMoney(double amount, String accountNumber) throws Exception {
                Account account = accountListingService.getClientAccount(accountNumber);

                transactionWithdraw.execute(account, amount);
        }

}
