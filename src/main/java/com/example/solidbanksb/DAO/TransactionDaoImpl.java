package com.example.solidbanksb.DAO;

import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.Transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDaoImpl implements TransactionDao{

    @Autowired
    TransactionRepository transactions;
    @Override
    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactions.findAll();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByClientId(String client_id) {
        return transactions.findByClientId(client_id);
    }

}
