package com.example.solidbanksb.service;

import com.example.solidbanksb.model.User.User;
import com.example.solidbanksb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public Integer getUserIdByUsername(String authenticatedUsername) {
        return userRepository.findByUsername(authenticatedUsername).orElseThrow(() -> new RuntimeException("User not found")).getId();
    }
}
