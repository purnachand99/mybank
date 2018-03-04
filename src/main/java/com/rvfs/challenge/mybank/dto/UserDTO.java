package com.rvfs.challenge.mybank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@JsonIgnoreProperties(value = {"password"},
        allowSetters = true)
public class UserDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String name;

    private AccountDTO account;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
