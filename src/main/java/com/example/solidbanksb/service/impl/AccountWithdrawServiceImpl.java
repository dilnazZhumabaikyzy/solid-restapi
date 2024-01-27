package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.exceptions.InsufficientFundsException;
import com.example.solidbanksb.exceptions.WithdrawalNotAllowedException;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.service.AccountWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public boolean withdraw(double amount, String accountId) throws Exception {
        Account account = accountDao.getClientAccount(accountId);
        if (!account.isWithdrawAllowed()){
            throw new WithdrawalNotAllowedException(accountId);
        }

        double newBalance = account.getBalance() - amount;


        if(newBalance < 0){
                throw new InsufficientFundsException(accountId, amount);
        }
        account.setBalance(newBalance);
        accountDao.updateAccount(account);
        return true;
    }
}
