package com.example.solidbanksb;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Transaction.Transaction;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
public class ResponseMessage{
    private String message;
    private List<Account> accountList;
    private List<Transaction> transactionList;
    private Account account;
    private Transaction transaction;
}
