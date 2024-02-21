package com.example.solidbanksb.DAO.Impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.exceptions.AccountNotFoundException;
import com.example.solidbanksb.exceptions.InvalidAccountUpdateException;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountDTO;
import com.example.solidbanksb.repository.AccountRepository;
import com.example.solidbanksb.model.Account.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private final AccountRepository accountRepository;
    List<Account> accountList = new ArrayList<>();
    @Override
    public List<AccountDTO> getClientAccounts(Integer clientId) {

        List<Account> accounts = (List<Account>) accountRepository.findByClientId(clientId);
        List<AccountDTO> accountDTOS = accounts.stream()
                                                        .map(account -> AccountDTO.builder()
                                                                .id(account.getId())
                                                                .accountType(account.getAccountType())
                                                                .clientId(account.getClient().getId())
                                                                .balance(account.getBalance())
                                                                .withdrawAllowed(account.isWithdrawAllowed())
                                                                .build())
                                                        .collect(Collectors.toList());

        return accountDTOS;
    }

    @Override
    public void createNewAccount(Account account) throws AccountCreationException {
        try {
            accountRepository.save(account);
        } catch (Exception e){
            throw new AccountCreationException(e.getMessage());
        }
    }

    @Override
    public void updateAccount(Account account) throws Exception {
        Account existingAccount = accountRepository.findById(account.getId())
                .orElseThrow(() -> new AccountNotFoundException(account.getId()));

        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setWithdrawAllowed(account.isWithdrawAllowed());

        try {
            accountRepository.save(existingAccount);
        } catch (Exception e){
            throw new InvalidAccountUpdateException(account.getId());
        }
    }

    @Override
    public List<Account> getClientAccountsByType(String clientId, AccountType accountType) {
        accountRepository.findByClientIdAndAccountType(Integer.parseInt(clientId), accountType.getType());
        List<Account> filteredAccounts = accountRepository.findByClientIdAndAccountType(Integer.parseInt(clientId), accountType.getType());

        if (filteredAccounts.isEmpty()) {
            throw new NoSuchElementException("No accounts found for client " + clientId + " with account type " + accountType);
        }

        return filteredAccounts;
    }

    @Override
    public Account getClientAccount(String accountId){
        return accountRepository.findById(accountId).orElseThrow(()-> new AccountNotFoundException(accountId));
    }

    @Override
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = (List<Account>)accountRepository.findAll();
        List<AccountDTO> accountDTOS = accounts.stream()
                .map(account -> AccountDTO.builder()
                        .id(account.getId())
                        .accountType(account.getAccountType())
                        .clientId(account.getClient().getId())
                        .balance(account.getBalance())
                        .withdrawAllowed(account.isWithdrawAllowed())
                        .build())
                .collect(Collectors.toList());
        return accountDTOS;
    }

    @Override
    public void deleteAccount(String accountId) {
        accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        accountRepository.deleteById(accountId);
    }
}
