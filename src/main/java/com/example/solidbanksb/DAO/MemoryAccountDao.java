package com.example.solidbanksb.DAO;

import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountRepository;
import com.example.solidbanksb.model.Account.AccountType;
import com.example.solidbanksb.model.Account.AccountWithdraw;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class MemoryAccountDao implements AccountDao{
    @Autowired
    private final AccountRepository accountRepository;
    List<Account> accountList = new ArrayList<>();
    @Override
    public List<Account> getClientAccounts(String clientId) {

        return (List<Account>) accountRepository.findByClientId(clientId);
    }

    @Override
    public void createNewAccount(Account account) {
        System.out.println("Account creation...");
        System.out.println(account.getId());
        accountRepository.save(account);
        System.out.println("Bank account created successfully");
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Account acc = accountRepository.findById(account.getId()).orElseThrow(() -> new Exception("Account not found"));
            acc.setAccountType(account.getAccountType());
            acc.setBalance(account.getBalance());
            acc.setWithdrawAllowed(account.isWithdrawAllowed());
            System.out.println("Bank account updated successfully");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public List<Account> getClientAccountsByType(String clientId, AccountType accountType) {
        accountRepository.findByClientIdAndAccountType(clientId, accountType);
        List<Account> filteredAccounts = accountRepository.findByClientIdAndAccountType(clientId, accountType);

        if (filteredAccounts.isEmpty()) {
            throw new NoSuchElementException("No accounts found for client " + clientId + " with account type " + accountType);
        }

        return filteredAccounts;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientId, String accountId) {
        Account account = accountRepository.findByClientIdAndAccountId(clientId, accountId);

        if (account == null) {
            throw new NoSuchElementException("No withdraw account found for client " + clientId + " with account id " + accountId);
        }

        return (AccountWithdraw) account;
    }

    @Override
    public Account getClientAccount(String clientId, String accountId) {
        Account account = accountRepository.findByClientIdAndAccountId(clientId, accountId);

        if (account == null) {
            throw new NoSuchElementException("No account found for client " + clientId + " with account id " + accountId);
        }

        return account;
    }
}
