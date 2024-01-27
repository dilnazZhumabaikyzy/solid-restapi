package com.example.solidbanksb.model.Account;

import com.example.solidbanksb.service.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountCreationService accountCreationService;
    @GetMapping()
    public String hello() {
        return "Hello World!";
    }
//    @PostMapping
//    public Long createAccount(@RequestBody StudentRequest studentRequest) {
//        return accountCreationService.create(studentRequest);
//    }
}
