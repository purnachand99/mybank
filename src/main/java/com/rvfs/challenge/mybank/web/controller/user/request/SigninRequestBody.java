package com.rvfs.challenge.mybank.web.controller.user.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Signin Request Body.
 */
public class SigninRequestBody implements Serializable{

    @NotBlank
    @Email
    @ApiModelProperty(notes = "User's email", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "Account password", required = true)
    private String password;

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

}
