package com.rvfs.challenge.mybank.service;

import com.rvfs.challenge.mybank.model.User;
import com.rvfs.challenge.mybank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public void signup(User user) {
        userRepository.save(user);
    }

    @Override
    public User signin(User user) {
        return null;
    }
}
