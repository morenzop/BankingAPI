package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Account;
import com.BankingAPI.BankingAPI.models.Customer;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import com.BankingAPI.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("/accounts")
    public ResponseEntity<?> List(){
        HttpStatus statusCode;
        Response response = new Response();
//        To check if a list is empty add .isEmpty to your method.
        if(accountsRepository.findAll().isEmpty()){
            response.setCode(404);
            response.setMessage("error fetching accounts");
//            StatusCode for 404.
                statusCode = HttpStatus.NOT_FOUND;
        }else {
            List<Account> r = accountsRepository.findAll();
            response.setCode(200);
            response.setMessage("Success");
            response.setData(r);
//            StatusCode for 200.
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<?> List(@PathVariable Long id ) {
        HttpStatus statusCode;
        Response response = new Response();
        if (!accountsRepository.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching account");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            Optional<Account> a = accountsRepository.findById(id);
            response.setCode(200);
            response.setMessage("error fetching account");
//            The singletonList() method of java.util.Collections class is used to return an immutable list containing only the specified object.
            response.setData(Collections.singletonList(a));
            statusCode = HttpStatus.OK;

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/customer/{id}/accounts")
    public ResponseEntity<?> getAccountsForCustomer(@PathVariable Long id){
        HttpStatus statusCode;
        Response response = new Response();
        if(!accountsRepository.existsById(id)){
            response.setCode(404);
            response.setMessage("error fetching customers account");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            customerRepository.findById(id);
//            Trying to find customers by ID.
            Optional<Customer> customer = customerRepository.findById(id);
            customer.ifPresent(customer1 -> {
                List<?> y = accountsRepository.findAllByCustomerId(customer.get().getId());
                response.setData(y);
            });
            response.setCode(200);
            response.setMessage("Success");
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @PostMapping("/customers/{id}/accounts")
    public ResponseEntity<?> createAccount(@RequestBody Account account, @PathVariable("id") long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()){
            response.setCode(404);
            response.setMessage("error creating customers account");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            account.setCustomerId(customer.get().getId());
            response.setCode(201);
            response.setMessage("Account created");
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> upDateAccounts(@RequestBody Account account, @PathVariable("id") long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Account> g = accountsRepository.findById(id);
        if(!accountsRepository.existsById(id)){
            response.setCode(404);
            response.setMessage("Error");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            accountsRepository.deleteById(id);
            Account commit = new Account();
            commit.setCustomerId(id);
            commit.setNickname(account.getNickname());
            commit.setType(account.getType());
            accountsRepository.save(commit);
            response.setCode(200);
            response.setMessage("Customer account updated");
            response.setData(Collections.singletonList(g));
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccounts(@RequestBody Account account, @PathVariable("id") long id){
        Response response = new Response();
        HttpStatus statusCode;
        if(!accountsRepository.existsById(id)) {
            response.setCode(404);
            response.setMessage("Account does not exists");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            accountsRepository.deleteById(id);
            response.setCode(202);
            response.setMessage("Accounted successfully deleted");
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(response, statusCode);
    }
}


