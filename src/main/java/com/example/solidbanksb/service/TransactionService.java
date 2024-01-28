package com.example.solidbanksb.service;

import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.Transaction.TransactionType;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();
    public  List<Transaction> getTransactionsForClient(String clientId);

    void create(boolean status, TransactionType transactionType, double amount, String accountId);
}
