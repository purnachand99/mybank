package com.rvfs.challenge.mybank.repository;

import com.rvfs.challenge.mybank.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for customer repository operations.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    /**
     * Find customer by UserId.
     * @param userId Id of the user.
     * @return Customer data.
     */
    Customer findByUserId(Long userId);
}
