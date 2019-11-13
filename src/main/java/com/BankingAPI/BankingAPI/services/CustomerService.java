package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Customer;
import com.BankingAPI.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

}

