package com.BankingAPI.BankingAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

//an entity represents a table in a relational database, and each entity instance corresponds to a row in that table
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// A list of named constant and defines a class type, Enumerations can have constructors, methods and instance variables.
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String nickname;
    private Integer rewards;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="payee_id")
    private Set<Deposit> deposits;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="payer_id")
    private Set<Withdrawal> withdrawals;

    private double balance;

    @JsonProperty("customer_id")
    private long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Double getBalance() {
        Double balance = 0.0;
        if (deposits != null) {
            for (Deposit i : deposits) {
                if (i.getMedium().equals(TransactionMedium.Balance) && i.getStatus().equals(TransactionStatus.Completed))
                    balance += i.getAmount();
            }
        }
        if (withdrawals != null) {
            for (Withdrawal i : withdrawals) {
                if (i.getMedium().equals(TransactionMedium.Balance) && i.getStatus().equals(TransactionStatus.Completed))
                    balance -= i.getAmount();
            }
        }
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Integer getRewards() {
        return this.rewards;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    public Set<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(Set<Withdrawal> withdrawals) {
        this.withdrawals = withdrawals;
    }
}
