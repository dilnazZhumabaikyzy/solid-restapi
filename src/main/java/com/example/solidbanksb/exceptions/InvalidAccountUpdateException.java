package com.example.solidbanksb.exceptions;

public class InvalidAccountUpdateException extends Exception {
    public InvalidAccountUpdateException(String accountId) {
        super(String.format("Error: Invalid account update for %s. Please check the provided details and try again.", accountId));
    }
}
