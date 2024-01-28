package com.example.solidbanksb.model.Transaction;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.DAO.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountDao accountDao;

    public List<Transaction> getAllTransactions(){
        return transactionDao.getTransactions();
    }
    public List<Transaction> getTransactionsForClient(String clientId){
        return transactionDao.getTransactionsByClientId(clientId);
    }

    @Override
    public void create(boolean status, TransactionType transactionType, double amount,String accountId){
        String clientId = String.valueOf(accountId.charAt(2));
        transactionDao.addTransaction(
                Transaction.builder()
                                    .transactionType(transactionType.getType())
                                    .date(new Date())
                                    .status(status ? "SUCCESSFULLY" : "DECLINED")
                                    .amount(amount)
                                    .clientId(clientId)
                                    .build());
    }
}
