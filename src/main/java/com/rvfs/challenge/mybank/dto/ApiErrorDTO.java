package com.rvfs.challenge.mybank.dto;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to carry Api error data
 */
public class ApiErrorDTO {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiErrorDTO(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorDTO(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.addError(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}