package com.BankingAPI.BankingAPI.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.persistence.*;
import java.util.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String first_name;
    private String last_name;
    private String email;
    private String password;

    // @ManyToOne(targetEntity = Address.class)
    @Lob
    @Column(name = "address", columnDefinition="CLOB")
    private ArrayList<Address> address = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public ArrayList<Address> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address.addAll(address);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
