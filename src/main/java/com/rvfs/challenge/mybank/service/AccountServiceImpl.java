package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account create(Account account)  {
        return accountRepository.save(account);
    }


    @Override
    public Account find(Long id) {
        return null;
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
