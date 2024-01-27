package com.example.solidbanksb.exceptions;

public class AccountCreationException extends RuntimeException{
    public AccountCreationException(String message){
        super("Account did not created. Error: " + message);
    }
}
