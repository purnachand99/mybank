package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.CustomerDTO;
import com.rvfs.challenge.mybank.model.Customer;

public interface CustomerService {

    CustomerDTO create(Customer customer);

    CustomerDTO find(Long id);
}
