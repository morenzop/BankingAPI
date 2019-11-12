package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Deposit;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import com.BankingAPI.BankingAPI.repositories.DepositsRepository;
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
    private DepositsRepository depositsRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/deposits/{id}")
    public ResponseEntity<?> getDepositById(@PathVariable long id) {
        HttpStatus statusCode;
        Response response = new Response();
        if (!depositsRepository.existsById(id)) {
            response.setCode(404);
            response.setMessage("Error fetching withdrawal with id: " + id);
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            Optional<Deposit> d = depositsRepository.findById(id);
            response.setCode(200);
            response.setData(new ArrayList<>(Collections.singleton(d)));
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/accounts/{id}/deposits")
    public ResponseEntity<?> getDepositsForAccount(@PathVariable String id){
        Response response = new Response();
        HttpStatus statusCode;
        if (!accountsRepository.existsById(Long.parseLong(id))){
            response.setCode(404);
            response.setMessage("Account not found");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            List<Deposit> d = depositsRepository.findAllByPayeeId(id);
            response.setCode(200);
            response.setData(d);
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PostMapping("/accounts/{id}/deposits")
    public ResponseEntity<?> createDeposit(@RequestBody Deposit deposit, @PathVariable String id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!accountsRepository.existsById(Long.parseLong(id))) {
            response.setCode(404);
            response.setMessage("Error creating deposit: Account not found.");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            response.setCode(201);
            response.setData(new ArrayList<>(Collections.singleton(deposit)));
            Deposit submit = new Deposit();
            submit.setAmount(deposit.getAmount());
            submit.setDescription(deposit.getDescription());
            submit.setId(deposit.getId());
            submit.setMedium(deposit.getMedium());
            submit.setTransaction_date(deposit.getTransaction_date());
            submit.setType(deposit.getType());
            submit.setStatus(deposit.getStatus());
            submit.setPayeeId(id);
            depositsRepository.save(submit);
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/deposits/{id}")
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!depositsRepository.existsById(id)) {
            response.setCode(404);
            response.setMessage("Error creating deposit: Account not found.");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            depositsRepository.deleteById(id);
            Deposit submit = new Deposit();
            submit.setAmount(deposit.getAmount());
            submit.setDescription(deposit.getDescription());
            submit.setId(id);
            submit.setMedium(deposit.getMedium());
            submit.setTransaction_date(deposit.getTransaction_date());
            submit.setType(deposit.getType());
            submit.setStatus(deposit.getStatus());
            submit.setPayeeId(deposit.getPayeeId());
            depositsRepository.save(submit);
            response.setCode(202);
            response.setMessage("Accepted deposit modification");
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/deposits/{id}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long id) {
        Response response = new Response();
        HttpStatus statusCode;
        if (!depositsRepository.existsById(id)) {
            response.setCode(404);
            response.setMessage("This ID does not exist in deposits");
            statusCode = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(response, statusCode);
        } else {
            depositsRepository.deleteById(id);
            statusCode = HttpStatus.NO_CONTENT;
            return new ResponseEntity<>(null, statusCode);
        }
    }
}
