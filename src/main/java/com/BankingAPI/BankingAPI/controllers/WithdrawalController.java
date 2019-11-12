package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.models.Withdrawal;
import com.BankingAPI.BankingAPI.repositories.WithdrawalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalsRepository withdrawalsRepository;

    @GetMapping("/withdrawals/{id}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable long id){
        Optional<Withdrawal> d = withdrawalsRepository.findById(id);
        Response response = new Response();
        response.setCode(200);
        response.setData(new ArrayList<>(Collections.singleton(d)));
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}/withdrawals")
    public ResponseEntity<?> getWithdrawalsForAccount(@PathVariable String id){
        List<Withdrawal> d = withdrawalsRepository.findAllByPayerId(id);
        Response response = new Response();
        response.setCode(200);
        response.setData(d);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/accounts/{id}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable String id) {
        Withdrawal submit = new Withdrawal();
        submit.setAmount(withdrawal.getAmount());
        submit.setDescription(withdrawal.getDescription());
        submit.setId(withdrawal.getId());
        submit.setMedium(withdrawal.getMedium());
        submit.setTransaction_date(withdrawal.getTransaction_date());
        submit.setType(withdrawal.getType());
        submit.setStatus(withdrawal.getStatus());
        submit.setPayerId(id);
        withdrawalsRepository.save(submit);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/withdrawals/{id}")
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable Long id) {
        withdrawalsRepository.deleteById(id);
        Withdrawal submit = new Withdrawal();
        submit.setAmount(withdrawal.getAmount());
        submit.setDescription(withdrawal.getDescription());
        submit.setId(id);
        submit.setMedium(withdrawal.getMedium());
        submit.setTransaction_date(withdrawal.getTransaction_date());
        submit.setType(withdrawal.getType());
        submit.setStatus(withdrawal.getStatus());
        submit.setPayerId(withdrawal.getPayerId());
        withdrawalsRepository.save(submit);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/withdrawals/{id}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long id) {
        withdrawalsRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
