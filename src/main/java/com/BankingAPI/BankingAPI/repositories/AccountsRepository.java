package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByCustomerId(long customerId);

     List<Account> findAccountsByCustomerId(long id);

}
