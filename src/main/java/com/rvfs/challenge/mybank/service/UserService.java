package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;

public interface UserService {

    UserDTO signup(UserDTO user) throws MyBankException;

    UserDTO signin(UserDTO user) throws MyBankException;
}
