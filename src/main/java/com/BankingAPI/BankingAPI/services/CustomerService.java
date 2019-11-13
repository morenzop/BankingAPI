package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Account;
import com.BankingAPI.BankingAPI.models.Customer;
import com.BankingAPI.BankingAPI.repositories.AccountsRepository;
import com.BankingAPI.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    public List<Account> findAccountsByCustomerId(long id){
        List<Account> result = new ArrayList<>();
        for (Account a : accountsRepository.findAll()) {
            if (a.getCustomerId() == id){
                result.add(a);
            }
        }
        return result;
    }

    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }
}
