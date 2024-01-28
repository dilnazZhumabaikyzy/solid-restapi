package com.example.solidbanksb.model.Transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@Data
@Entity(name = "TRANSACTION_TABLE")
@Builder
@AllArgsConstructor
public class Transaction{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String transactionType;
    private String clientId;
    private String status;
    private double amount;


    @Override
    public String toString() {
        return "Transaction " +
                "\n* Id: " + id +
                "\n* date: " + date +
                "\n* Transaction Type: " + transactionType  +
                "\n* Client Id:" + clientId +
                "\n* Amount: " + amount +
                "\n* Status: " + status;
    }
}