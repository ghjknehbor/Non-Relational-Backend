package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Security.JwtUtil;
import com.example.demo.model.Villa_Owner;
import com.example.demo.repository.Villa_OwnerRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/villa_owner")
public class Villa_OwnerController {
    @Autowired
    private Villa_OwnerRepository ownerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Villa_Owner createVilla_Owner(@RequestBody Villa_Owner villa_Owner) {

        villa_Owner.setPassword(passwordEncoder.encode(villa_Owner.getPassword()));
        return ownerRepository.save(villa_Owner);
        
    }

    @PostMapping("/auth/login")
        public ResponseEntity<?> login(@RequestBody Villa_Owner loginRequest) throws Exception {
            
            Villa_Owner owner = ownerRepository.findByEmail(loginRequest.getEmail());
            if (owner == null || !passwordEncoder.matches(loginRequest.getPassword(), owner.getPassword())) {
                throw new Exception("Invalid email or password");
            }
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwtUtil.generateToken(owner.getEmail()));

            return ResponseEntity.ok(tokenResponse);
        }

    @GetMapping
    public List<Villa_Owner> getAllVilla_Owners() {
        return ownerRepository.findAll();
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