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



//    @Override
//    public String toString() {
//        return "\nAccount information \n" +
//                " - accountType: " + accountType +
//                " - id: " + id +
////                " - id: " + String.format("%03d%06d\n", 1, Integer.parseInt(id)) +
//                " - clientId: " + clientId + "\n"+
//                " - balance: " + balance +  "\n"+
//                " - withdrawAllowed=" + withdrawAllowed;
//    }
}
