package com.example.solidbanksb.model.Transaction;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();
    public  List<Transaction> getTransactionsForClient(String clientId);

    void create(boolean status, TransactionType transactionType, double amount,String accountId);
}
