package com.example.solidbanksb.model.Account;

//import jakarta.persistence.*;
import lombok.*;




@NoArgsConstructor
@Data
public class AccountTest {

    private  String id;
    private AccountType accountType;
    private String clientId;
    private double balance = 0;
    private boolean withdrawAllowed;


    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setWithdrawAllowed(boolean withdrawAllowed) {
        this.withdrawAllowed = withdrawAllowed;
    }

    public String getId() {
        return id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getClientId() {
        return clientId;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isWithdrawAllowed() {
        return withdrawAllowed;
    }

    @Override
    public String toString() {
        return "\nAccount information \n" +
                " - accountType: " + accountType +
                " - id: " + id +
//                " - id: " + String.format("%03d%06d\n", 1, Integer.parseInt(id)) +
                " - clientId: " + clientId + "\n"+
                " - balance: " + balance +  "\n"+
                " - withdrawAllowed=" + withdrawAllowed;
    }
}
