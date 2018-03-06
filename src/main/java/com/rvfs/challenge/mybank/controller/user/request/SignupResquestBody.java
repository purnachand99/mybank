package com.rvfs.challenge.mybank.controller.user.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Signup Request Body.
 */
public class SignupResquestBody implements Serializable{

    @NotBlank
    @Email
    @ApiModelProperty(notes = "User's email", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "Account password", required = true)
    private String password;

    @ApiModelProperty(notes = "User's name", required = true)
    private String name;

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
}
