package com.example.solidbanksb;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountBasicCli;
import com.example.solidbanksb.model.Account.AccountRepository;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.TransactionDeposit.TransactionDepositCLI;
import com.example.solidbanksb.model.TransactionTransfer.TransactionTransferCLI;
import com.example.solidbanksb.model.TransactionTransfer.TransferResult;
import com.example.solidbanksb.model.TransactionTransfer.TransferType;
import com.example.solidbanksb.model.TransactionWithdraw.TransactionWithdrawCLI;
import com.example.solidbanksb.service.MyCLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication

public class SolidbankSbApplication  implements CommandLineRunner {

    final static String HELPER_MSG = "1 - showAccounts\n2 - create account\n3 - deposit\n4 - withdraw\n5 - transfer\n6 - this message\n7 - exit\n8 - test accountRepository";

    @Autowired
    AccountBasicCli accountBasicCli;
    @Autowired
    TransactionDepositCLI  transactionDepositCLI;
    @Autowired
    TransactionWithdrawCLI transactionWithdrawCLI;
    @Autowired
    TransactionTransferCLI transactionTransferCLI;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MyCLI myCLI;

    public static void main(String[] args) {
        SpringApplication.run(SolidbankSbApplication.class);
    }

    @Override
    public void run(String... arg0) throws Exception {
        boolean running = true;
        String clientId = null;
        boolean isClientIdSet = false;
        System.out.println("Welcome to CLIBank!");
        System.out.println(HELPER_MSG);

        while (running){
            try {
                if(!isClientIdSet){
                    System.out.println("\nWrite Your client ID:");
                    clientId = myCLI.requestClientId();
                    isClientIdSet = true;
                }
                System.out.println("\nPlease enter the command number:");
                switch (myCLI.getScanner().nextLine()){
                    case "1":
                        accountBasicCli.getAccounts(clientId);
                        break;
                    case "2":
                        accountBasicCli.createAccountRequest(myCLI.requestAccountType(), clientId);
                        break;
                    case "3":{
                        AccountType accountType = myCLI.requestAccountType();
                        String accountNumber= myCLI.requestClientAccountNumber();
                        double depositAmount = myCLI.requestClientAmount();

                        transactionDepositCLI.depositMoney(depositAmount, accountType, accountNumber, clientId);
                        break;
                    }
                    case "4":
                        AccountType accountType = myCLI.requestAccountType();
                        String accountNumber= myCLI.requestClientAccountNumber();
                        double withdrawAmount = myCLI.requestClientAmount();

                        transactionWithdrawCLI.withdrawMoney(withdrawAmount, accountType, accountNumber, clientId);
                        break;
                    case "5":{
                        TransferType transferType = myCLI.requestTransferType();
                        AccountType fromAccountTypeChoice = myCLI.requestAccountType();
                        String fromAccountNumber= myCLI.requestClientAccountNumber();
                        double transferAmount = myCLI.requestClientAmount();


                        TransferResult transferResult = myCLI.processTransfer(transferType, clientId);


                        transactionTransferCLI.transfer(transferAmount,
                                fromAccountTypeChoice,
                                fromAccountNumber,
                                clientId,
                                transferResult.getToAccountType(),
                                transferResult.getToAccountNumber(),
                                transferResult.getToClientId(),
                                transferType);
                        break;
                    }

                    case "6":
                        System.out.println(HELPER_MSG);
                        break;
                    case "7":
                        running = false;
                        System.out.println("Exit");

                }
            }
            catch (Exception e){
                System.out.println("Wrong input");
                System.out.println(e.fillInStackTrace());
            }
        }
    }
}
