package com.rvfs.challenge.mybank.repository;

import com.rvfs.challenge.mybank.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByAccountNumber(Long accountNumber);
}
