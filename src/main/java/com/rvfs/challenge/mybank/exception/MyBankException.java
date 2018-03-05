package com.rvfs.challenge.mybank.exception;

/**
 * Exceptions of MyBank Application.
 */
public class MyBankException extends Exception{

    public MyBankException() {
    }

    public MyBankException(String message) {
        super(message);
    }

    public MyBankException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBankException(Throwable cause) {
        super(cause);
    }

    public MyBankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
