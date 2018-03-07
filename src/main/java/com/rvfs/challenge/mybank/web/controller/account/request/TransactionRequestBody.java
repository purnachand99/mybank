package com.rvfs.challenge.mybank.web.controller.account.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Request Body for transactions
 */
public class TransactionRequestBody implements Serializable {

    @NotNull
    @ApiModelProperty(notes = "Account number for transaction", required = true)
    private Long accountNumber;

    @NotNull
    @ApiModelProperty(notes = "Amount", required = true)
    private BigDecimal amount;

    @ApiModelProperty(notes = "Transaction description")
    private String description;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
