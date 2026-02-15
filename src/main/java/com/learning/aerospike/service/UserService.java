package com.learning.aerospike.service;

import com.learning.aerospike.model.UserRequest;
import com.learning.aerospike.repository.User;
import com.learning.aerospike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User userEntity) {
        // Business Logic: e.g., Validate email format here
        userRepository.save(userEntity);
        System.out.println("Service: User registered successfully -> " + userEntity.getId());
    }

    public User getUserDetails(String id) {
        return userRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public List<User> getUsersByDepartment(String department) {
        return userRepository.findByDepartment(department);
    }
}