package com.example.solidbanksb.model.TransactionTransfer;

import com.example.solidbanksb.DAO.TransactionDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.Transaction.TransactionType;
import com.example.solidbanksb.service.AccountDepositService;
import com.example.solidbanksb.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@Component
public class TransactionTransfer {
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    AccountDepositService accountDepositService;
    @Autowired
    AccountWithdrawService accountWithdrawService;



    public void execute(Account fromAccount, Account toAccount, double transferAmount) throws Exception {
        boolean withdrawStatus = accountWithdrawService.withdraw(transferAmount, fromAccount);
        boolean depositStatus = false;
        if (withdrawStatus){
            depositStatus = accountDepositService.deposit(transferAmount, toAccount);
        }


        Transaction transaction = new Transaction();
        transaction.setDate(new Date());
        transaction.setTransactionType(TransactionType.TRANSFER.getType());
        transaction.setClientId(fromAccount.getClientId());
        transaction.setAmount(transferAmount);
        transaction.setStatus(withdrawStatus && depositStatus ? "SUCCESSFULLY" : "DECLINED");

        transactionDao.addTransaction(transaction);

        System.out.println("Transaction created");
        System.out.println("Info: " + transaction);
    }

}
