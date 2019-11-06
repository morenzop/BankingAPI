package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Withdrawal;
import com.BankingAPI.BankingAPI.repositories.WithdrawalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WithdrawalService {

    @Autowired
    private WithdrawalsRepository withdrawalsRepository;

    public List<Withdrawal> findAllByAccountId(String id){
        List<Withdrawal> result = new ArrayList<>();
        for (Withdrawal w : withdrawalsRepository.findAll()) {
            if (w.getPayerId().equals(id)){
                result.add(w);
            }
        }
        return result;
    }
}
