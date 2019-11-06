package com.BankingAPI.BankingAPI.controllers;

import com.BankingAPI.BankingAPI.models.Customer;
import com.BankingAPI.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    public List<Customer> List(){
        return customerRepository.findAll();
    }
}
