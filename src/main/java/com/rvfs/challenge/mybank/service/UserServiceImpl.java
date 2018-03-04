package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
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
        Customer savedCustomer = customerService.create(customer);

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
    public UserDTO signin(UserDTO user) {

        User loggedUser = userRepository.findByEmail(user.getEmail());

        if(loggedUser!= null && StringUtils.pathEquals(loggedUser.getPassword(), user.getPassword())) {

            System.out.println(ObjectParserUtil.getInstance().toString(loggedUser));
            // getting customer data
            Customer customer = customerService.find(loggedUser.getId());
            System.out.println(ObjectParserUtil.getInstance().toString(customer));
            user.setName(customer.getName());

            // getting account data
            Account account = accountService.find(loggedUser.getId());

            AccountDTO userAccount = new AccountDTO();
            userAccount.setAccountNumber(account.getAccountNumber());
            userAccount.setCurrentBalance(account.getBalance());
            userAccount.setUpdateAt(account.getUpdatedAt());

            user.setAccount(userAccount);
            System.out.println(ObjectParserUtil.getInstance().toString(user));

        } else {
            return null;
        }
        return user;
    }
}
