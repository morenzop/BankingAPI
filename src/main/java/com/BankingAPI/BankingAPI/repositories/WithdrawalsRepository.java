package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalsRepository extends JpaRepository<Withdrawal, Long> {

    List<Withdrawal> findAllByPayerId(String payerId);
}
