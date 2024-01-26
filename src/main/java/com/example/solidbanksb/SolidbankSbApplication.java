package com.example.solidbanksb;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountBasicCli;
import com.example.solidbanksb.model.Account.AccountRepository;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.Transaction.TransactionManagerCLI;
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

    final static String HELPER_MSG = "1 - showAccounts\n2 - create account\n3 - deposit\n4 - withdraw\n5 - transfer\n6 - show my transactions\n7 - show all transactions\n8 - this message\n9 - change client ID\n10 - exit";

    @Autowired
    AccountBasicCli accountBasicCli;
    @Autowired
    TransactionDepositCLI  transactionDepositCLI;
    @Autowired
    TransactionWithdrawCLI transactionWithdrawCLI;
    @Autowired
    TransactionTransferCLI transactionTransferCLI;
    @Autowired
    private TransactionManagerCLI transactionManagerCLI;
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
                        String accountNumber= myCLI.requestClientAccountNumber();
                        double depositAmount = myCLI.requestClientAmount();

                        transactionDepositCLI.depositMoney(accountNumber, depositAmount);
                        break;
                    }
                    case "4":
//                      //CHECKING, SAVING
                        String accountNumber= myCLI.requestClientAccountNumber();
                        double withdrawAmount = myCLI.requestClientAmount();

                        transactionWithdrawCLI.withdrawMoney(withdrawAmount,accountNumber);
                        break;
                    case "5":{
                        String fromAccountNumber= myCLI.requestClientAccountNumber();
                        double transferAmount = myCLI.requestClientAmount();

                        TransferType transferType = myCLI.requestTransferType();

                        TransferResult transferResult = myCLI.processTransfer(transferType, clientId, transferAmount);


                        transactionTransferCLI.transfer(
                                fromAccountNumber,
                                transferResult.getToAccountNumber(),
                                transferResult.getTransferAmount()
                        );
                        break;
                    }
                    case "6":
                        transactionManagerCLI.getTransactionsForClient(clientId);
                       break;
                    case "7":
                        transactionManagerCLI.getAllTransactions();
                        break;
                    case "8":
                        System.out.println(HELPER_MSG);
                        break;
                    case "9":
                        System.out.println("\nWrite Your client ID:");
                        clientId = myCLI.requestClientId();
                        break;
                    case "10":
                        running = false;
                        System.out.println("Exit");
                        System.exit(0);
                }
            }
            catch (Exception e){
                System.out.println("Wrong input");
                System.out.println(e.fillInStackTrace());
            }
        }
    }
}
