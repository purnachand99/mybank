package com.rvfs.challenge.mybank.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Calendar;

public class AccountDTO {

    private Long accountNumber;

    private BigDecimal currentBalance;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Calendar updateAt;

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
}