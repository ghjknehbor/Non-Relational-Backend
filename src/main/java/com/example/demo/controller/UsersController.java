package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/create_user") // this is basically register lol
    public Users createUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Search for a user with the provided email and password
        Users user = usersRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            // Return userId if login is successful
            Map<String, String> response = new HashMap<>();
            response.put("userId", user.getId());
            return ResponseEntity.ok(response);
        } else {
            // Return an error response if login fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid email or password");
        }
    }
}
