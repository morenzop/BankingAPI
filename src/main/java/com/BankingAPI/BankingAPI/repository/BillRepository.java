package com.BankingAPI.BankingAPI.repository;

import com.BankingAPI.BankingAPI.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository<Bills> extends CrudRepository<Bill, Long> {

    List<Bills> findAccountById(long accountId);
}
