package com.rvfs.challenge.mybank.repository;

import com.rvfs.challenge.mybank.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for account repository operations.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    /*@EntityGraph(value = "allTransactions" , type= EntityGraph.EntityGraphType.LOAD)*/
    Account findByAccountNumber(Long accountNumber);
}
