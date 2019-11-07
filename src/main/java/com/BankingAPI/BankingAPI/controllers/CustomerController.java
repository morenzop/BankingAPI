package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.customers.Customer;
import com.BankingAPI.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public List<Customer> List(){
        return customerRepository.findAll();
    }
    public Optional<Customer> get
}
