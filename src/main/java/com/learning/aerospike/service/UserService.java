package com.learning.aerospike.service;

import com.learning.aerospike.model.User;
import com.learning.aerospike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(String id, String name, String email, int exp) {
        // Business Logic: e.g., Validate email format here
        User user = new User(id, name, email, exp);
        userRepository.save(user);
        System.out.println("Service: User registered successfully -> " + id);
    }

    public User getUserDetails(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }
}