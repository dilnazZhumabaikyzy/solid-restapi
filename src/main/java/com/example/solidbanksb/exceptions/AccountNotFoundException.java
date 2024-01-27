package com.example.solidbanksb.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String id) {
        super(String.format("Could not find account %s", id));
    }
}