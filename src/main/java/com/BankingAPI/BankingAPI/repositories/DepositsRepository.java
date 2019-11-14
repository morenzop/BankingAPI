package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositsRepository extends JpaRepository<Deposit, Long> {

    List<Deposit> findAllByPayeeId(Long payeeId);
}
