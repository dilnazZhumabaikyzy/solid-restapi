package com.example.solidbanksb;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountDTO;
import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.User.User;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
public class ResponseMessage{
    private String message;
    private List<AccountDTO> accountList;
    private List<Transaction> transactionList;
    private List<User> userList;
    private Account account;
    private Transaction transaction;
}
