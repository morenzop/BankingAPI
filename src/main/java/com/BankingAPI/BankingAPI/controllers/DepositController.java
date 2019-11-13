package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Deposit;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import com.BankingAPI.BankingAPI.repositories.DepositsRepository;
import com.BankingAPI.BankingAPI.services.AccountService;
import com.BankingAPI.BankingAPI.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/deposits/{id}")
    public ResponseEntity<?> getDepositById(@PathVariable long id){
        Optional<Deposit> d = depositService.findById(id);
        Response response = new Response();
        response.setCode(200);
        response.setData(new ArrayList<>(Collections.singleton(d)));
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}/deposits")
    public ResponseEntity<?> getDepositsForAccount(@PathVariable String id){
        List<Deposit> d = depositService.findAllByAccountId(id);
        Response response = new Response();
        response.setCode(200);
        response.setData(d);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/accounts/{id}/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit, @PathVariable String id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!accountService.existsById(Long.parseLong(id))) {
            response.setCode(404);
            response.setMessage("Error creating deposit: Account not found.");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            response.setCode(201);
            Deposit submit = depositService.createDeposit(deposit, id);
            response.setData(new ArrayList<>(Collections.singleton(submit)));
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/deposits/{id}")
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long id) {
        depositService.updateDeposit(deposit, id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/deposits/{id}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long id) {
        depositService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
