package com.example.bank_app.service;
import com.example.bank_app.model.Account;
import com.example.bank_app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountDetails(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
