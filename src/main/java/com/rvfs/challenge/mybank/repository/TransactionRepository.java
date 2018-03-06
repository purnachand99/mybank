package com.rvfs.challenge.mybank.repository;

import com.rvfs.challenge.mybank.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for transactions repository operations.
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Find all transactions by account number.
     * @param accountNumber Account number.
     * @return All transactions of the account number.
     */
    List<Transaction> findByAccountAccountNumber(Long accountNumber);
}
