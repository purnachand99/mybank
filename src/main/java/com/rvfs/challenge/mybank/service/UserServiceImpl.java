package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.repository.UserRepository;
import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerService customService;

    @Autowired
    private AccountService accountService;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void signup(UserDTO user) {

        User savedUser = userRepository.save(new User(user.getEmail(), user.getPassword()));

        Customer customer = new Customer(user.getName());
        customer.setUser(savedUser);

        ObjectParserUtil.getInstance().toString(customer);

        Customer savedCustomer = customService.create(customer);

        Account account = new Account();
        account.setCustomer(savedCustomer);
        account.setAccountNumber(Calendar.getInstance().getTimeInMillis());

        ObjectParserUtil.getInstance().toString(account);

        Account savedAccount = accountService.create(account);

        ObjectParserUtil.getInstance().toString(savedAccount);

    }

    @Override
    public User signin(UserDTO user) {
        return null;
    }
}
