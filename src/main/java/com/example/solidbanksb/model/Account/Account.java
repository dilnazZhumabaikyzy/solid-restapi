package com.example.solidbanksb.model.Account;

//import jakarta.persistence.*;
import com.example.solidbanksb.model.User.User;
import jakarta.persistence.*;
import lombok.*;




@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Builder
public class Account{
    private @Id String id;
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "client_id") // Name of the foreign key column in the ACCOUNT table
    private User client;
    private double balance = 0;
    private boolean withdrawAllowed;


    @Override
    public String toString() {
        return "\nAccount information \n" +
                " - accountType: " + accountType + "\n" +
                " - id: " + id + "\n" +
                " - clientId: " + client.getId() + "\n"+
                " - balance: " + balance +  "\n"+
                " - withdrawAllowed=" + withdrawAllowed;
    }
}
