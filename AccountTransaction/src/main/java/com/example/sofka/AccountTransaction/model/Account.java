package com.example.sofka.AccountTransaction.model;

import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column("accountNumber")
    private String accountNumber;

    @Column("accountType")
    private String accountType;

    @Column("openingBalance")
    private Double openingBalance;

    @Column("state")
    private AccountState state;

    @Column("clientId")
    private Long clientId;

    public Account(String accountNumber, String accountType, Double openingBalance, AccountState state, Long clientId) {
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.openingBalance = openingBalance;
        this.state = state;
    }

    public Account(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public AccountState getState() {
        return state;
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    public enum AccountState {
        ACTIVA,
        INACTIVA
    }
}

