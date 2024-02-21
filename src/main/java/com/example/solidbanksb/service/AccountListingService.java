package com.example.solidbanksb.service;


import com.example.solidbanksb.model.Account.Account;
import com.example.solidbanksb.model.Account.AccountDTO;
import com.example.solidbanksb.model.Account.AccountType;

import java.util.List;

public interface AccountListingService {

    List<AccountDTO> getClientAccounts();

    List<AccountDTO> getAccounts();

}

