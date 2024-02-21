package com.example.solidbanksb.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getUserIdByUsername(String authenticatedUsername) {
        return userRepository.findByUsername(authenticatedUsername).orElseThrow(() -> new RuntimeException("User not found")).getId();
    }
}
