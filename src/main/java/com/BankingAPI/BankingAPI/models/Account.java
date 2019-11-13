package com.BankingAPI.BankingAPI.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

//an entity represents a table in a relational database, and each entity instance corresponds to a row in that table
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
// A list of named constant and defines a class type, Enumerations can have constructors, methods and instance variables.
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String nickname;
    private int rewards;
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

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Double getBalance() {
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

    /*@Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type=" + type +
                ", nickname='" + nickname + '\'' +
                ", rewards=" + rewards +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }*/
}
