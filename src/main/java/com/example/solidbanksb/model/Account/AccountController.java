package com.example.solidbanksb.model.Account;

import com.example.solidbanksb.ResponseMessage;
import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.exceptions.InsufficientFundsException;
import com.example.solidbanksb.exceptions.WithdrawalNotAllowedException;
import com.example.solidbanksb.service.AccountDepositService;
import com.example.solidbanksb.service.AccountService;
import com.example.solidbanksb.service.AccountListingService;
import com.example.solidbanksb.service.AccountWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public List<Account> getAccounts() {
        return accountListingService.getAccounts();
    }
    @GetMapping("/{client_id}/client")
    public List<Account> getClientAccounts(@PathVariable String client_id) {
        return accountListingService.getClientAccounts(client_id);
    }
    @GetMapping("/{account_id}")
    public Account getAccount(@PathVariable String account_id) throws Exception {
        return accountService.getAccount(account_id);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createAccount(@RequestBody AccountRequest accountRequest) {
        try{
            accountService.create(accountRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Account created"));
        }
        catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage(new AccountCreationException(e.getMessage()).getMessage()));
        }
    }
    @DeleteMapping("/{account_id}")
    public ResponseEntity<ResponseMessage> deleteAccount(@PathVariable String account_id) {
        try{
            accountService.deleteAccount(account_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(String.format("Account with ID %s successfully deleted", account_id)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Account deletion failed. Error: " + e.getMessage()));
        }
    }

    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<ResponseMessage> withdraw(@PathVariable String account_id, @RequestParam("amount") String amount){
        try{
            accountWithdrawService.withdraw(Double.parseDouble(amount),account_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(String.format("Withdrawal of %s from account ID %s successful", amount, account_id)));
        }
        catch (WithdrawalNotAllowedException | InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Withdrawal failed. Error: " + e.getMessage()));
        }
    }
    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<ResponseMessage> deposit(@PathVariable String account_id, @RequestParam("amount") String amount){
        try{
            accountDepositService.deposit(Double.parseDouble(amount), account_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(String.format("Deposit of %s from account ID %s successful", amount, account_id)));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Deposit failed. Error: " + e.getMessage()));
        }
    }
}
