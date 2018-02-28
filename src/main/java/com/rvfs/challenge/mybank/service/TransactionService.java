package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Transaction;

public interface TransactionService {

    void withdraw(Transaction transaction);

    void deposit(Transaction transaction);

    void getBalanceAndStatement(Account account);


}
