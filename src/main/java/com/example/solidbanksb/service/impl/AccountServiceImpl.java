package com.example.solidbanksb.service.impl;

import com.example.solidbanksb.DAO.AccountDao;
import com.example.solidbanksb.exceptions.AccountCreationException;
import com.example.solidbanksb.model.Account.*;
import com.example.solidbanksb.model.User.User;
import com.example.solidbanksb.repository.UserRepository;
import com.example.solidbanksb.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void create(AccountRequest accountRequest) throws AccountCreationException {
        System.out.println("Account creation...");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int clientId = 0;
        if (authentication != null && authentication.isAuthenticated()) {

            Object principal = authentication.getPrincipal();

            if (principal instanceof User user) {
                clientId = user.getId();
            }
        }


        Optional<User> userOptional = userRepository.findById(clientId);
        User client = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String accountId = generateAccountId(clientId);
                Account acc = Account.builder().
                        accountType(accountRequest.getAccountType()).
                        balance(accountRequest.getBalance()).
                        withdrawAllowed(accountRequest.isWithdrawAllowed()).
                        client(client).
                        id(accountId).build();

               accountDao.createNewAccount(acc);
    }
    public  String generateAccountId(int clientId) {
        return String.format("%03d%06d", clientId, getLastAccountId());
    }

    private int getLastAccountId() {
        String lastAccountId = entityManager.createQuery("SELECT MAX(SUBSTR(a.id, 5)) FROM Account a", String.class)
                .getSingleResult();
        if(lastAccountId == null){
            lastAccountId = "0";
        }
        return (Integer.parseInt(lastAccountId) + 1);
    }
    @Override
    public Account getAccount(String accountId) throws Exception {
        return accountDao.getClientAccount(accountId);
    }

    @Override
    public void deleteAccount(String accountId) {
        accountDao.deleteAccount(accountId);
    }
}
