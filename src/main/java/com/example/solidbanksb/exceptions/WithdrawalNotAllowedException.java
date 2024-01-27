package com.example.solidbanksb.exceptions;

public class WithdrawalNotAllowedException extends Exception{
    public WithdrawalNotAllowedException(String accountId){
        super(String.format("Error: Withdrawal is not allowed for account ID %s.", accountId));
    };
}
