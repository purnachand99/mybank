package com.rvfs.challenge.mybank.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rvfs.challenge.mybank.util.serializer.CalendarSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Data Transfer Object for Account informations.
 */
public class AccountDTO {

    private Long accountNumber;

    private BigDecimal currentBalance;

    private List<TransactionDTO> transactions;

    @JsonSerialize(using=CalendarSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Calendar updateAt;

    public AccountDTO() {
    }

    public AccountDTO(Long accountNumber, BigDecimal currentBalance, Calendar updateAt) {
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.updateAt = updateAt;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Calendar getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Calendar updateAt) {
        this.updateAt = updateAt;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(TransactionDTO transaction){
        if(this.transactions == null){
            this.transactions = new ArrayList<>();
        }
        this.transactions.add(transaction);
    }
}
