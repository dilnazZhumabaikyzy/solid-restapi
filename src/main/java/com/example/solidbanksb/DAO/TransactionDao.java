package com.example.solidbanksb.DAO;

import com.example.solidbanksb.model.Transaction.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);

    List<Transaction> getTransactionsByClientId(String client_id);
}
