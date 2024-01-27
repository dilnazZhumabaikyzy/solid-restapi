package com.example.solidbanksb.model.TransactionDeposit;

import com.example.solidbanksb.DAO.TransactionDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.Transaction.TransactionType;
import com.example.solidbanksb.service.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class TransactionDeposit {

    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountDepositService accountDepositService;
    public void execute(Account account, double amount) throws Exception {
        boolean status = accountDepositService.deposit(amount, account.getId());

        Transaction transaction = new Transaction();
        transaction.setDate(new Date());
        transaction.setTransactionType(TransactionType.DEPOSIT.getType());
        transaction.setClientId(account.getClientId());
        transaction.setAmount(amount);
        transaction.setStatus(status ? "SUCCESSFULLY" : "DECLINED");
        transactionDao.addTransaction(transaction);

        System.out.println("Transaction created");
        System.out.println("Info: " + transaction);
    }

}

