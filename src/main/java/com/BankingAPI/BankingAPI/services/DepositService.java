package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Deposit;
import com.BankingAPI.BankingAPI.repositories.DepositsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepositService {

    @Autowired
    private DepositsRepository depositsRepository;

    public List<Deposit> findAllByAccountId(String id){
        List<Deposit> result = new ArrayList<>();
        for (Deposit d : depositsRepository.findAll()) {
            if (d.getPayeeId().equals(id)){
                result.add(d);
            }
        }
        return result;
    }
}
