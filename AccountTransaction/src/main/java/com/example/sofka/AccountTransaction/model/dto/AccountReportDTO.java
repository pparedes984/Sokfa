package com.example.sofka.AccountTransaction.model.dto;

import com.example.sofka.AccountTransaction.model.Account;
import com.example.sofka.AccountTransaction.model.Transaction;

import java.util.List;

public class AccountReportDTO {

    private Long accountId;
    private String accountNumber;
    private Double currentBalance;
    private String accountType;
    private List<Transaction> transactions;

    public AccountReportDTO(Account account, List<Transaction> transactions) {
        this.accountId = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.accountType = account.getAccountType();
        this.currentBalance = account.getOpeningBalance();
        this.transactions = transactions;
    }

    // Getters y Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // toString (opcional)
    @Override
    public String toString() {
        return "AccountReportDTO{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", transactions=" + transactions +
                '}';
    }
}
