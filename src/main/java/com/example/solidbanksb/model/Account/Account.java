package com.example.solidbanksb.model.Account;

//import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;




@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Account {

    private @Id String id;
    private String accountType;
    private String clientId;
    private double balance = 0;
    private boolean withdrawAllowed;



    @Override
    public String toString() {
        return "\nAccount information \n" +
                " - accountType: " + accountType + "\n" +
                " - id: " + id + "\n" +
                " - clientId: " + clientId + "\n"+
                " - balance: " + balance +  "\n"+
                " - withdrawAllowed=" + withdrawAllowed;
    }
}
