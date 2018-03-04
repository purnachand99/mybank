package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer find(Long id) {
        return customerRepository.findOne(id);
    }
}
