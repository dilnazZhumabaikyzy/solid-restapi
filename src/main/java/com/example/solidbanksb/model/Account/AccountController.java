package com.example.solidbanksb.model.Account;

import com.example.solidbanksb.ResponseMessage;
import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.exceptions.InsufficientFundsException;
import com.example.solidbanksb.exceptions.WithdrawalNotAllowedException;
import com.example.solidbanksb.model.Transaction.Transaction;
import com.example.solidbanksb.model.Transaction.TransactionService;
import com.example.solidbanksb.model.Transaction.TransactionType;
import com.example.solidbanksb.service.AccountDepositService;
import com.example.solidbanksb.service.AccountService;
import com.example.solidbanksb.service.AccountListingService;
import com.example.solidbanksb.service.AccountWithdrawService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountListingService accountListingService;
    @Autowired
    private AccountWithdrawService accountWithdrawService;
    @Autowired
    private AccountDepositService accountDepositService;
    @Autowired
    private TransactionService transactionService;
    @GetMapping()
    public ResponseEntity<ResponseMessage>  getAccounts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.builder().message("All accounts from database retrieved").accountList(accountListingService.getAccounts()).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseMessage.builder().message("Couldn't retrieve accounts. Error: " + e.getMessage()).build());
        }
    }
    @GetMapping("/{client_id}/client")
    public ResponseEntity<ResponseMessage> getClientAccounts(@PathVariable String client_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.builder().message(String.format("All accounts of Client %s database retrieved", client_id)).accountList(accountListingService.getClientAccounts(client_id)).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseMessage.builder().message(String.format("Couldn't retrieve accounts of Client %s. Error: %s", client_id, e.getMessage())).build());
        }
    }
    @GetMapping("/{account_id}")
    public ResponseEntity<ResponseMessage> getAccount(@PathVariable String account_id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.builder().message(String.format("Account  wit ID %s retrieved", account_id)).account(accountService.getAccount(account_id)).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseMessage.builder().message(String.format("Couldn't retrieve account with ID %s. Error: %s", account_id, e.getMessage())).build());
        }
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createAccount(@RequestBody AccountRequest accountRequest) {
        try{
            accountService.create(accountRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder().message("Account created").build());
        }
        catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseMessage.builder().message(new AccountCreationException(e.getMessage()).getMessage()).build());
        }
    }
    
    @DeleteMapping("/{account_id}")
    public ResponseEntity<ResponseMessage> deleteAccount(@PathVariable String account_id) {
        try{
            accountService.deleteAccount(account_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder().message(String.format("Account with ID %s successfully deleted", account_id)).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseMessage.builder().message("Account deletion failed. Error: " + e.getMessage()).build());
        }
    }

    @Transactional
    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<ResponseMessage> withdraw(@PathVariable String account_id, @RequestParam("amount") String amount) {
        boolean status = false;
        try {
            status = accountWithdrawService.withdraw(Double.parseDouble(amount), account_id);
        }
        catch (WithdrawalNotAllowedException | InsufficientFundsException e) {
            transactionService.create(status, TransactionType.WITHDRAWAL, Double.parseDouble(amount),  account_id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.builder().message(e.getMessage()).build());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.builder().message("Withdrawal failed. Error: " + e.getMessage()).build());
        }

        transactionService.create(status, TransactionType.WITHDRAWAL, Double.parseDouble(amount), account_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder().message(String.format("Withdrawal of %s from account ID %s successful", amount, account_id)).build());
    }
    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<ResponseMessage> deposit(@PathVariable String account_id, @RequestParam("amount") String amount){
        boolean status = false;
        try{
            status = accountDepositService.deposit(Double.parseDouble(amount), account_id);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.builder().message("Deposit failed. Error: " + e.getMessage()).build());
        }

        transactionService.create(status, TransactionType.DEPOSIT, Double.parseDouble(amount), account_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder().message(String.format("Deposit of %s from account ID %s successful", amount, account_id)).build());
    }
    @GetMapping("/transactions")
    public ResponseEntity<ResponseMessage> getTransactions(){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder().message("All transactions from database retrieved").transactionList(transactionService.getAllTransactions()).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.builder().message("Couldn't retrieve transaction. Error: " + e.getMessage()).build());
        }
    }
    @GetMapping("/transactions/{client_id}")
    public ResponseEntity<ResponseMessage> getTransactions(@PathVariable String client_id){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder().message("All transactions from database retrieved. Client ID: " + client_id).transactionList(transactionService.getTransactionsForClient(client_id)).build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.builder().message("Couldn't retrieve transaction. Error: " + e.getMessage()).build());
        }
    }
}
