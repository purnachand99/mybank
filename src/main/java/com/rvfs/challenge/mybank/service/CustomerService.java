package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.CustomerDTO;
import com.rvfs.challenge.mybank.model.Customer;

/**
 * Customer service interface.
 */
public interface CustomerService {

    /**
     * Create a new customer.
     * @param customer Customer data.
     * @return New Customer.
     */
    CustomerDTO create(Customer customer);

    /**
     * Find a customer by id.
     * @param id Customer id.
     * @return Customer data.
     */
    CustomerDTO find(Long id);
}
