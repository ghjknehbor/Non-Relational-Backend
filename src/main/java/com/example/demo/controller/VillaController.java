package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Location;
import com.example.demo.model.Villa;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.VillaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/villa")
public class VillaController {
    @Autowired
    private VillaRepository villaRepository;
    @Autowired
    private LocationRepository locationRepository;
    @PostMapping("/create_villa")

    public Villa createVilla(@RequestBody Villa villa) {
        Location location = locationRepository.save(villa.getLocation());
        return villaRepository.save(villa);
    }

    @GetMapping("/show_villa")
    public List<Villa> getAllVillas() {
        return villaRepository.findAll();
    }
        // UsersController should be mostly done here
}