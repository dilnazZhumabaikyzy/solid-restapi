package com.example.solidbanksb.model.Account;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRequest {
    private String accountType;
    private String clientId;
    private double balance = 0;
    private boolean withdrawAllowed;
}
