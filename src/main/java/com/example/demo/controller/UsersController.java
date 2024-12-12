package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Security.JwtUtil;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register") // this is basically register lol
    public Users createUser(@RequestBody Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Users loginRequest) throws Exception {
        // Find user by email
        Users user = usersRepository.findByEmail(loginRequest.getEmail());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new Exception("Invalid email or password");
        }
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwtUtil.generateToken(user.getEmail()));

        // Generate JWT token directly after verifying password
        return ResponseEntity.ok(tokenResponse);
    }
    @PostMapping("/auth/getEmail")
    public ResponseEntity<?> getEmailFromToken(@RequestHeader("Authorization") String token) {
    // Remove "Bearer " from the token string
    String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
    
    String email;
    try {
        email = jwtUtil.extractEmail(jwtToken);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    return ResponseEntity.ok(new respond("success", List.of(email)));
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class respond {
    String status = "success";
    List<?> data;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TokenResponse {
    String status = "success";
    String token;
}
}

