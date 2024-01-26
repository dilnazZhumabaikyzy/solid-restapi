package com.example.solidbanksb.model.Transaction;

import com.example.solidbanksb.model.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Transaction {
    private Date date;
    private TransactionType transactionType;
    private Account account;

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", transactionType=" + transactionType +
                ", account=" + account +
                '}';
    }
}