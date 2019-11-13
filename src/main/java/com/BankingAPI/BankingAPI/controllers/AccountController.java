package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Account;
import com.BankingAPI.BankingAPI.models.Customer;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.services.AccountService;
import com.BankingAPI.BankingAPI.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {



    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/accounts")
    public ResponseEntity<?> List(){
        HttpStatus statusCode;
        Response response = new Response();
//        To check if a list is empty add .isEmpty to your method.
        if(accountService.findAll().isEmpty()){
            response.setCode(404);
            response.setMessage("error fetching accounts");
//            StatusCode for 404.
                statusCode = HttpStatus.NOT_FOUND;
        }else {
            List<Account> r = accountService.findAll();
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
        if (!accountService.existsById(id)) {
            response.setCode(404);
            response.setMessage("error fetching account");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            Optional<Account> a = accountService.findById(id);
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
        if(!customerService.existsById(id)){
            response.setCode(404);
            response.setMessage("error fetching customers account");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
//            Trying to find customers by ID.
            Optional<Customer> customer = customerService.findById(id);
            customer.ifPresent(customer1 -> {
                List<?> y = accountService.findAllByCustomerId(customer.get().getId());
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
        Optional<Customer> customer = customerService.findById(id);
        if(!customer.isPresent()){
            response.setCode(404);
            response.setMessage("error creating customers account");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            // account.setCustomerId(id);
//            StatusCode for 201
            accountService.save(account);
            response.setCode(201);
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(account);
            response.setData(accounts);
            response.setMessage("Account created");
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> upDateAccounts(@RequestBody Account account, @PathVariable("id") long id){
        Response response = new Response();
        HttpStatus statusCode;
        Optional<Account> g = accountService.findById(id);
        if(!accountService.existsById(id)){
            response.setCode(404);
            response.setMessage("Error");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            accountService.deleteById(id);
            Account commit = new Account();
            commit.setCustomerId(id);
            commit.setNickname(account.getNickname());
            commit.setType(account.getType());
            accountService.save(commit);
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
        if(!accountService.existsById(id)) {
            response.setCode(404);
            response.setMessage("Account does not exists");
            statusCode = HttpStatus.NOT_FOUND;
        }else{
            accountService.deleteById(id);
            response.setCode(202);
            response.setMessage("Accounted successfully deleted");
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(response, statusCode);
    }
}
