package com.example.solidbanksb.service;

import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.Account.SavingAccount;
import com.example.solidbanksb.model.CLIUI;
import com.example.solidbanksb.model.TransactionTransfer.TransferResult;
import com.example.solidbanksb.model.TransactionTransfer.TransferType;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class MyCLI implements CLIUI {
    private Scanner scanner;

    public MyCLI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public AccountType requestAccountType() {
       try{
           System.out.println("Choose account type: [CHECKING, SAVING, FIXED]");
           String accountType = scanner.nextLine().toUpperCase();
           return switch (accountType) {
               case "SAVING" -> AccountType.valueOf("SAVING");
               case "CHECKING" -> AccountType.valueOf("CHECKING");
               case "FIXED" -> AccountType.valueOf("FIXED");
               default -> throw new IllegalArgumentException("Invalid account type");
           };
       } catch (IllegalArgumentException e) {
           System.out.println("Invalid account type.");
           return requestAccountType();
       }
    }


    @Override
    public double requestClientAmount(){
        System.out.println("Write amount: ");
        return Double.parseDouble(scanner.nextLine());
    }

    @Override
    public String requestClientAccountNumber() {
        System.out.println("Write account number:");
        return scanner.nextLine();
    }

    public Scanner getScanner() {
        return scanner;
    }

    @Override
    public int chooseTheOption() {
        return Integer.parseInt(scanner.nextLine());
    }


    @Override
    public TransferResult processTransfer(TransferType transferType, String clientId) {
        TransferResult transferResult = new TransferResult();
        switch (transferType){
            case TRANSFER_TO_ANOTHER_CLIENT_ACCOUNT -> {
                transferResult.setToClientId(requestClientId());
            }
            case TRANSFER_BETWEEN_OWN_ACCOUNTS -> {
                transferResult.setToClientId(clientId);
            }
        }
        transferResult.setToAccountType(requestAccountType());
        transferResult.setToAccountNumber(requestClientAccountNumber());
        return transferResult;
    }

    @Override
    public TransferType requestTransferType() {
        try{
            System.out.println("Choose the option:");
            System.out.println("1. Transfer to another client's account");
            System.out.println("2. Transfer between own accounts");
            int transferOption = scanner.nextInt();

            return switch(transferOption) {
                case 1 -> TransferType.TRANSFER_TO_ANOTHER_CLIENT_ACCOUNT;
                case 2 -> TransferType.TRANSFER_BETWEEN_OWN_ACCOUNTS;
                default -> throw new IllegalArgumentException("Invalid account type");
            };
        }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid account type.");
                return requestTransferType();
            }

    }

    public String requestClientId() {return scanner.nextLine();}
}