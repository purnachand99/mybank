package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Customer;

public interface CustomerService {

    void create(Customer customer);

    Customer find(Long id);
}
