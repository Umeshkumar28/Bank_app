package com.example.bank_app.controller;

import com.example.bank_app.model.Account;
import com.example.bank_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountNumber}")
    public Account getAccountDetails(@PathVariable String accountNumber) {
        return accountService.getAccountDetails(accountNumber);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }
}
