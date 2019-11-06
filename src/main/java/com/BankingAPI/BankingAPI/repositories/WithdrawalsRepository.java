package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalsRepository extends JpaRepository<Withdrawal, Long> {

    Iterable<Withdrawal> getWithdrawalsByPayerId(String account);
}
