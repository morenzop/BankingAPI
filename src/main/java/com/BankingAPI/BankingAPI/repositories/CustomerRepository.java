package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {



}
