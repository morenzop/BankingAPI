package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Account;
import com.BankingAPI.BankingAPI.models.Address;
import com.BankingAPI.BankingAPI.models.Customer;
import com.BankingAPI.BankingAPI.models.Response;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import com.BankingAPI.BankingAPI.repositories.CustomerRepository;
import com.BankingAPI.BankingAPI.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.DataInput;
import java.io.IOException;
import java.util.*;

@RestController
public class CustomerController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
   private AccountsRepository accountsRepository;

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        Response response=new Response();
        HttpStatus statusCode;
        if (customerRepository.findAll().isEmpty()) {
            response.setCode(404);
            response.setMessage("Error fetching accounts");
            statusCode = HttpStatus.NOT_FOUND;
        }else{

        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/accounts/customerId}/customers")
    public ResponseEntity<?> getAccountForCustomer(@PathVariable long id){
        Response response = new Response();
        HttpStatus statusCode;
        if (!accountsRepository.existsById(id)){
            response.setCode(404);
            response.setMessage("Error fetching customer accounts");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            List<Account> a = customerService.findAccountsByCustomerId(id);
            response.setCode(200);
            response.setData(a);
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id) {
        HttpStatus statusCode;
        Response response = new Response();
        if (!customerRepository.existsById(id)) {
            response.setCode(404);
            response.setMessage("Error fetching account: " + id);
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            Optional<Customer> c = customerRepository.findById(id);
            response.setCode(200);
            response.setMessage("Success");
            response.setData(new ArrayList<>(Collections.singleton(c)));
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) throws IOException {
        Response response= new Response();
        response.setCode(201);
        response.setMessage("Customer account created");
        Customer c = new Customer();
        c.setFirst_name(customer.getFirst_name());
        c.setLast_name(customer.getLast_name());
        c.setEmail(customer.getEmail());
        c.setId(customer.getId());
        c.setPassword(customer.getPassword());
        // c.setAddress(Arrays.asList(addresses));
        response.setData(new ArrayList<>(Collections.singleton(customer)));
        customerRepository.save(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
