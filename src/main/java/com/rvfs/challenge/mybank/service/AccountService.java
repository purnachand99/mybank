package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.TransactionDTO;
import com.rvfs.challenge.mybank.exception.BusinessException;
import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Transaction;

public interface AccountService {

    AccountDTO create(Account account);

    AccountDTO find(Long id);

    AccountDTO findByAccountNumber(Long accountNumber);

    AccountDTO withdraw(TransactionDTO transaction) throws BusinessException;

    AccountDTO deposit(TransactionDTO transaction) throws BusinessException;

    AccountDTO getStatement(AccountDTO account);

}
