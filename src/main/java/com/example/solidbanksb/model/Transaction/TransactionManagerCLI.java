package com.example.solidbanksb.model.Transaction;

import com.example.solidbanksb.DAO.TransactionDao;
import com.example.solidbanksb.model.Account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionManagerCLI {
    @Autowired
    TransactionDao transactionDao;

    public void getAllTransactions(){
        List<Transaction> transactions = transactionDao.getTransactions();

        if(transactions.isEmpty()){
            System.out.println("No transactions found");
        } else {
            System.out.printf("\nTransactions(%d): \n", transactions.size());
            for(Transaction transaction: transactions){
                System.out.println(transaction);
                System.out.println("-----------------------------------");
            }
        }
    }
    public void getTransactionsForClient(String clientId){
        List<Transaction> transactions = transactionDao.getTransactionsByClientId(clientId);

        if(transactions.isEmpty()){
            System.out.println("No transactions found for client: " + clientId);
        } else {
            System.out.printf("\nTransactions(%d): \n", transactions.size());
            for(Transaction transaction: transactions){
                System.out.println(transaction);
                System.out.println("-----------------------------------");
            }
        }
    }
}
