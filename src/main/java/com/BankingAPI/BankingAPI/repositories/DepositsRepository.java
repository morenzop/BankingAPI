package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositsRepository extends JpaRepository<Deposit, Long> {

    Iterable<Deposit> getDepositsByPayeeId(String account);
}
