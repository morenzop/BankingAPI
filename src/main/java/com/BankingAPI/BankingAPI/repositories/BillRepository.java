package com.BankingAPI.BankingAPI.repositories;

import com.BankingAPI.BankingAPI.models.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

    List<Bill> findAccountById(long accountId);

}
