package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account, Long> {
}
