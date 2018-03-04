package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.dto.UserDTO;
import com.rvfs.challenge.mybank.model.User;

public interface UserService {

    UserDTO signup(UserDTO user);

    UserDTO signin(UserDTO user);
}
