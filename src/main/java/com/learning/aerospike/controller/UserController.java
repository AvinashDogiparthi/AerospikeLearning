package com.learning.aerospike.controller;

import com.learning.aerospike.mapper.UserMapper;
import com.learning.aerospike.model.UserRequest;
import com.learning.aerospike.repository.User;
import com.learning.aerospike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    // Endpoint to Create/Update a User
    // Usage: POST http://localhost:8080/api/users
    // Body: { "id": "dev_002", "name": "Rahul", "email": "rahul@example.com", "experience": 5 }
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRequest userRequest) {
        User userEntity = userMapper.toEntity(userRequest);
        userService.registerUser(userEntity);
        return ResponseEntity.ok("User registered successfully: " + userEntity.getId());
    }

    // Endpoint to Get a User
    // Usage: GET http://localhost:8080/api/users/dev_002
    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUser(@PathVariable String id) {
        try {
            User user = userService.getUserDetails(id);
            UserRequest userRequest = userMapper.toDto(user);
            return ResponseEntity.ok(userRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to Get Users by Department
    // Usage: GET http://localhost:8081/department/Engineering
    @GetMapping("/department/{dept}")
    public ResponseEntity<List<UserRequest>> getUsersByDept(@PathVariable("dept") String dept) {
        List<User> users = userService.getUsersByDepartment(dept);
        List<UserRequest> userRequests = userMapper.toDtos(users);
        return ResponseEntity.ok(userRequests);
    }
}