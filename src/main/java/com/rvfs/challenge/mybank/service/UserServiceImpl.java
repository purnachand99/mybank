package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.AccountDTO;
import com.rvfs.challenge.mybank.dto.CustomerDTO;
import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.model.Account;
import com.rvfs.challenge.mybank.model.Customer;
import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.repository.UserRepository;
import com.rvfs.challenge.mybank.util.ObjectParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    MessageSource messageSource;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public UserDTO signup(UserDTO user) throws MyBankException {
        LOGGER.debug("signup {}", ObjectParserUtil.getInstance().toString(user));

        if (user == null) {
            throw new MyBankException(messageSource.getMessage("error.invalid.credentials", null, Locale.getDefault()));
        }

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

        LOGGER.debug("signup savedAccount {}", ObjectParserUtil.getInstance().toString(savedAccount));

        // updating user data
        user.setAccount(savedAccount);

        LOGGER.debug("signup return {}", ObjectParserUtil.getInstance().toString(user));

        return user;
    }

    @Override
    public UserDTO signin(UserDTO user) throws MyBankException {
        LOGGER.debug("signin {}", ObjectParserUtil.getInstance().toString(user));
        if (user == null) {
            throw new MyBankException(messageSource.getMessage("error.invalid.credentials", null, Locale.getDefault()));
        }

        User loggedUser = userRepository.findByEmail(user.getEmail());
        if (loggedUser != null && StringUtils.pathEquals(loggedUser.getPassword(), user.getPassword())) {

            // getting customer data
            CustomerDTO customer = customerService.find(loggedUser.getId());
            user.setName(customer.getName());

            // getting account data
            AccountDTO account = accountService.find(loggedUser.getId());
            user.setAccount(account);

            LOGGER.debug("signin {}", ObjectParserUtil.getInstance().toString(user));

        } else {
            throw new MyBankException(messageSource.getMessage("error.invalid.credentials", null, Locale.getDefault()));
        }
        return user;
    }
}
