package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.exception.MyBankException;
import com.rvfs.challenge.mybank.model.User;

public interface UserService {

    UserDTO signup(UserDTO user) throws MyBankException;

    UserDTO signin(UserDTO user) throws MyBankException;
}
