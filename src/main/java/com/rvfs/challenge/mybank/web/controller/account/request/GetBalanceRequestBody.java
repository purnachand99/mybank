package com.rvfs.challenge.mybank.web.controller.account.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Request body to get balance and statemens status.
 */
public class GetBalanceRequestBody implements Serializable {

    @NotBlank
    @ApiModelProperty(notes = "Account number", required = true)
    private Long accountNumber;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
