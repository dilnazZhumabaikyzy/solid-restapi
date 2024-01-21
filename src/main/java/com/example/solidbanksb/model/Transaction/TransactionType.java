package com.example.solidbanksb.model.Transaction;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW"),
    TRANSFER("TRANSFER");
    private String type;

    public String getType() {
        return type;
    }

    TransactionType(String type){
        this.type = type;
    }
}
