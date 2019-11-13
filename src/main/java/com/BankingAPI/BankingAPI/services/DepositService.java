package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Deposit;
import com.BankingAPI.BankingAPI.models.Withdrawal;
import com.BankingAPI.BankingAPI.repositories.DepositsRepository;
import com.BankingAPI.BankingAPI.repositories.WithdrawalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Hopefully this class is depricated.
 * I'll find out once I can test the methods declared in the repository.
 */

@Component
public class DepositService {

    @Autowired
    private DepositsRepository depositsRepository;

    public List<Deposit> findAllByAccountId(String id){
        return depositsRepository.findAllByPayeeId(id);
    }

    public Optional<Deposit> findById(long id) {
        return depositsRepository.findById(id);
    }

    public void updateDeposit(Deposit deposit, long id) {
        Deposit submit = new Deposit();
        submit.setAmount(deposit.getAmount());
        submit.setDescription(deposit.getDescription());
        submit.setId(id);
        submit.setMedium(deposit.getMedium());
        submit.setTransaction_date(deposit.getTransaction_date());
        submit.setType(deposit.getType());
        submit.setStatus(deposit.getStatus());
        submit.setPayeeId(deposit.getPayeeId());
        depositsRepository.save(submit);
    }

    public void deleteById(Long id) {
        depositsRepository.deleteById(id);
    }

    public Deposit createDeposit(Deposit deposit, String id) {
        Deposit submit = new Deposit();
        submit.setAmount(deposit.getAmount());
        submit.setDescription(deposit.getDescription());
        submit.setId(deposit.getId());
        submit.setMedium(deposit.getMedium());
        submit.setTransaction_date(deposit.getTransaction_date());
        submit.setType(deposit.getType());
        submit.setStatus(deposit.getStatus());
        submit.setPayeeId(id);
        depositsRepository.save(submit);
        return submit;
    }
}
