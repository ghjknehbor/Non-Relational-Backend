package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Villa_Owner;
import com.example.demo.repository.Villa_OwnerRepository;
@RestController
@RequestMapping("/api/users")
public class Villa_OwnerController {
    @Autowired
    private Villa_OwnerRepository villa_OwnerRepository;

    @PostMapping
    public Villa_Owner createVilla_Owner(@RequestBody Villa_Owner villa_Owner) {
        return villa_OwnerRepository.save(villa_Owner);
    }

    @GetMapping
    public List<Villa_Owner> getAllVilla_Owners() {
        return villa_OwnerRepository.findAll();
    }
    // this is just saving and fetching, havent implemented the logic yet
}