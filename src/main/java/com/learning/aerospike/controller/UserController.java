package com.learning.aerospike.controller;

import com.learning.aerospike.model.User;
import com.learning.aerospike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to Create/Update a User
    // Usage: POST http://localhost:8080/api/users
    // Body: { "id": "dev_002", "name": "Rahul", "email": "rahul@example.com", "experience": 5 }
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user.getId(), user.getName(), user.getEmail(), user.getExperience());
        return ResponseEntity.ok("User registered successfully: " + user.getId());
    }

    // Endpoint to Get a User
    // Usage: GET http://localhost:8080/api/users/dev_002
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        try {
            User user = userService.getUserDetails(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}