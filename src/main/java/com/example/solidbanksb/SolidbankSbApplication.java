package com.example.solidbanksb;

import com.example.solidbanksb.model.Account.AccountBasicCli;
import com.example.solidbanksb.model.Transaction.TransactionServiceImpl;
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
    private TransactionServiceImpl transactionManagerCLI;
    @Autowired
    private MyCLI myCLI;

    public static void main(String[] args) {
        SpringApplication.run(SolidbankSbApplication.class);
    }

    @Override
    public void run(String... arg0) {

    }
}
