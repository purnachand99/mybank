package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.repository.UserRepository;
import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    public UserDTO signup(UserDTO user) {

        // creating user
        User savedUser = userRepository.save(new User(user.getEmail(), user.getPassword()));

        // creating customer
        Customer customer = new Customer(user.getName());
        customer.setUser(savedUser);
        Customer savedCustomer = customService.create(customer);

        // creating account
        Account account = new Account();
        account.setCustomer(savedCustomer);
        account.setBalance(BigDecimal.ZERO);
        Account savedAccount = accountService.create(account);

        // updating user data
        user.setAccount(new AccountDTO());
        user.getAccount().setAccountNumber(savedAccount.getAccountNumber());
        user.getAccount().setUpdateAt(savedAccount.getUpdatedAt());

        return user;
    }

    @Override
    public User signin(UserDTO user) {
        return null;
    }
}
