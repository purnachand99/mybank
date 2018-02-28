package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.User;

public interface UserService {

    void signup(User user);

    User signin(User user);
}
