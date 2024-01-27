package com.example.solidbanksb.exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String accointId, double amount) {
        super(String.format("Error: Insufficient funds in account ID %s for withdrawal of %f.",accointId, amount));
    }
}
