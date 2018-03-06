package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.CustomerDTO;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Customer service implementation.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDTO create(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerDTO(savedCustomer.getName());
    }

    @Override
    public CustomerDTO find(Long id) {
        Customer savedCustomer = customerRepository.findOne(id);

        return new CustomerDTO(savedCustomer.getName());

    }
}
