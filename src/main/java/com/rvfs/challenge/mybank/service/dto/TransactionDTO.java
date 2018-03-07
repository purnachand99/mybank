package com.rvfs.challenge.mybank.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rvfs.challenge.mybank.util.serializer.CalendarSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Transaction Data Transfer Object.
 */
public class TransactionDTO {

    @NotNull
    private Long accountNumber;

    @NotNull
    private BigDecimal amount;

    private BigDecimal balance;

    private String description;

    private String type;

    @JsonSerialize(using = CalendarSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Calendar createdAt;

    public TransactionDTO() {
    }

    public TransactionDTO(Long accountNumber, BigDecimal amount, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
    }

    public TransactionDTO(Long accountNumber, BigDecimal amount, BigDecimal balance, String description, String type, Calendar createdAt) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }
}
