package com.example.solidbanksb.model.TransactionTransfer;

import com.example.solidbanksb.model.Account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TransferResult {
    private AccountType toAccountType;
    private  String toAccountNumber;
    private  String toClientId;
    private double transferAmount;
}