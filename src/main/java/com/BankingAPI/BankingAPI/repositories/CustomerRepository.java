package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
