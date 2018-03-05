package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.CustomerDTO;
import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.repository.UserRepository;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerService customerService;

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
        customer.setId(savedUser.getId());
        customerService.create(customer);

        // creating account
        Account account = new Account();
        account.setId(savedUser.getId());
        account.setCustomer(customer);
        account.setBalance(BigDecimal.ZERO);
        AccountDTO savedAccount = accountService.create(account);

        // updating user data
        user.setAccount(savedAccount);
        return user;
    }

    @Override
    public UserDTO signin(UserDTO user) {

        User loggedUser = userRepository.findByEmail(user.getEmail());

        if(loggedUser!= null && StringUtils.pathEquals(loggedUser.getPassword(), user.getPassword())) {

            System.out.println(ObjectParserUtil.getInstance().toString(loggedUser));
            // getting customer data
            CustomerDTO customer = customerService.find(loggedUser.getId());
            System.out.println(ObjectParserUtil.getInstance().toString(customer));
            user.setName(customer.getName());

            // getting account data
            AccountDTO account = accountService.find(loggedUser.getId());
            user.setAccount(account);

            System.out.println(ObjectParserUtil.getInstance().toString(user));

        } else {
            return null;
        }
        return user;
    }
}
