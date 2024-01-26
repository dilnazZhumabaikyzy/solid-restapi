package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.service.AccountWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public boolean withdraw(double amount, Account account) throws Exception {
        if (!account.isWithdrawAllowed()){
            throw new Exception("Withdraw is not allowed");
        }

        System.out.println("\nOld balance -------- " + account.getBalance());
        System.out.println("Transfer amount ------- "  + amount);

        double newBalance = account.getBalance() - amount;
        System.out.println("New Balance --------- "+newBalance+"\n");

        try {
            if(newBalance < 0){
                throw new Exception("Unable to complete the withdrawal operation. Account balance is too low.");
            }
            account.setBalance(newBalance);
            accountDao.updateAccount(account);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
