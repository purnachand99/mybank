package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Account;

public interface AccountService {

    Account create(Account account);

    Account find(Long id);

    Account findByAccountNumber(Long accountNumber);

}
