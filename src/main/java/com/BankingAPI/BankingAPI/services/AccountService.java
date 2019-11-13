package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Account;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountService accountService;

    public Optional<Account> findById(long id){
        return accountsRepository.findById(id);
    }

    public boolean existsById(long id) {
        return accountsRepository.existsById(id);
    }

    public void deleteById(long id) {
        accountsRepository.deleteById(id);
    }

    public void save(Account commit) {
        accountsRepository.save(commit);
    }

    public List<?> findAllByCustomerId(Long id) {
        return accountsRepository.findAllByCustomerId(id);
    }

    public List<Account> findAll() {
        return accountsRepository.findAll();
    }
}
