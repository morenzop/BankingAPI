package com.BankingAPI.BankingAPI.services;

import com.BankingAPI.BankingAPI.models.Withdrawal;
import com.BankingAPI.BankingAPI.repositories.WithdrawalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WithdrawalService {

    @Autowired
    private WithdrawalsRepository withdrawalsRepository;

    public List<Withdrawal> findAllByAccountId(String id){
        return withdrawalsRepository.findAllByPayerId(id);
    }

    public Optional<Withdrawal> findById(long id) {
        return withdrawalsRepository.findById(id);
    }

    public void updateWithdrawal(Withdrawal withdrawal, long id) {
        Withdrawal submit = new Withdrawal();
        submit.setAmount(withdrawal.getAmount());
        submit.setDescription(withdrawal.getDescription());
        submit.setId(withdrawal.getId());
        submit.setMedium(withdrawal.getMedium());
        submit.setTransaction_date(withdrawal.getTransaction_date());
        submit.setType(withdrawal.getType());
        submit.setStatus(withdrawal.getStatus());
        submit.setPayerId(Long.toString(id));
        withdrawalsRepository.save(submit);
    }

    public void deleteById(Long id) {
        withdrawalsRepository.deleteById(id);
    }

    public Withdrawal createWithdrawal(Withdrawal withdrawal, String id) {
        Withdrawal submit = new Withdrawal();
        submit.setAmount(withdrawal.getAmount());
        submit.setDescription(withdrawal.getDescription());
        submit.setId(withdrawal.getId());
        submit.setMedium(withdrawal.getMedium());
        submit.setTransaction_date(withdrawal.getTransaction_date());
        submit.setType(withdrawal.getType());
        submit.setStatus(withdrawal.getStatus());
        submit.setPayerId(id);
        withdrawalsRepository.save(submit);
        return submit;
    }
}
