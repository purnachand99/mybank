package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;

/**
 * User service interface.
 */
public interface UserService {

    /**
     * Sign up a user.
     * @param user User.
     * @return User registered
     * @throws MyBankException If occurs an error during the method execution.
     */
    UserDTO signup(UserDTO user) throws MyBankException;

    /**
     * Sign in a user.
     *
     * @param user User.
     * @return User signed.
     * @throws MyBankException If occurs an error during the method execution.
     */
    UserDTO signin(UserDTO user) throws MyBankException;
}
