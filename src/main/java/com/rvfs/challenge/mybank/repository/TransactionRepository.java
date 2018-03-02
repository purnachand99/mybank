package com.rvfs.challenge.mybank.repository;

import com.rvfs.challenge.mybank.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}