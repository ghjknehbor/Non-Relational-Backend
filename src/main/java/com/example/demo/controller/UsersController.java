package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping
    public Users createUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
        // UsersController should be mostly done here
}