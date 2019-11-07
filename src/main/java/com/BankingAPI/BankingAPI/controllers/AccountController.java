package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Account;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    AccountsRepository accountsRepository;

    @GetMapping("/accounts")
    public ResponseEntity<?> List(){
        HttpStatus statusCode;
        Response response = new Response();
        if(accountsRepository.findAll() == (accountsRepository));
    }
    @GetMapping("/account/id")
    public Optional<Account> getAccount(Long id){
        return accountsRepository.findById(id);
    }
    @GetMapping("/{id}/account")
    public Account get(@PathVariable("id") Long id){
        return accountsRepository.getOne(id);

    }
    @PostMapping("/{id}/account")
    public void create(@RequestBody Account account, @PathVariable("id") long id){
        account.setId(id);
        accountsRepository.save(account);
    }
    @PutMapping("/account/{id}")
    public void upDateAccounts(@RequestBody Account account, @PathVariable("id") long id){


    }
    @DeleteMapping("/account/{id}")
    public void deleteAccounts(@RequestBody Account account, @PathVariable("id") long id){

    }
}


