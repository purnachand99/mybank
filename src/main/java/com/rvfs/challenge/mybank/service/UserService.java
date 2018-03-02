package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.dto.UserDTO;

public interface UserService {

    UserDTO signup(UserDTO user);

    User signin(UserDTO user);
}
