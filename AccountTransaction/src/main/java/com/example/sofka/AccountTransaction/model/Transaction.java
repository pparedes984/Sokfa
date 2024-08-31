package com.example.sofka.AccountTransaction.model;

import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column("date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column("transactionType")
    private String transactionType;

    @Column("value")
    private Double value;

    @Column("balance")
    private Double balance;

    @Column("accountId")
    private Long accountId;

    public Transaction(LocalDateTime date, String transactionType, Double value, Double balance, Long accountId) {
        this.date = date;
        this.transactionType = transactionType;
        this.value = value;
        this.balance = balance;
        this.accountId = accountId;
    }

    public Transaction(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getAccount() {
        return accountId;
    }

    public void setAccount(Long accountId) {
        this.accountId = accountId;
    }

}
