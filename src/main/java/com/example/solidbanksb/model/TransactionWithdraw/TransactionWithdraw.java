package com.example.solidbanksb.model.TransactionWithdraw;

import com.example.solidbanksb.DAO.TransactionDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.Transaction.TransactionType;
import com.example.solidbanksb.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@AllArgsConstructor
@Component
public class TransactionWithdraw {
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountWithdrawService accountWithdrawService;
    public void execute(Account account, double amount) throws Exception {

        boolean status =  accountWithdrawService.withdraw(amount, account.getId());

        Transaction transaction = new Transaction();
        transaction.setDate(new Date());
        transaction.setTransactionType(TransactionType.WITHDRAWAL.getType());
        transaction.setClientId(account.getClientId());
        transaction.setStatus(status ? "SUCCESSFULLY" : "DECLINED");
        transactionDao.addTransaction(transaction);

        System.out.println("\nTransaction successfully created");
        System.out.println("Info: " + transaction);
    }

}
