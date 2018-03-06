package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.model.Account;

public interface AccountService {

    AccountDTO create(Account account);

    AccountDTO find(Long id) throws MyBankException;

    AccountDTO findByNumber(Long accountNumber) throws MyBankException;

    AccountDTO withdraw(TransactionDTO transaction) throws MyBankException;

    AccountDTO deposit(TransactionDTO transaction) throws MyBankException;

    AccountDTO getStatement(AccountDTO account) throws MyBankException;

}
