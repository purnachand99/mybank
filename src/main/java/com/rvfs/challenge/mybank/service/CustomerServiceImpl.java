package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.repository.CustomerRepository;
import com.rvfs.challenge.mybank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer find(Long id) {
        return null;
    }
}
