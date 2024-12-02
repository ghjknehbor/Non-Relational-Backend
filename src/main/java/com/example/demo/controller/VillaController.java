package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Update Villa
    @PutMapping("/update_villa/{id}")
    public Villa updateVilla(@PathVariable String id, @RequestBody Villa villa) {
        Optional<Villa> existingVilla = villaRepository.findById(id);
        
        if (existingVilla.isPresent()) {
            Villa updatedVilla = existingVilla.get();
            updatedVilla.setVilla_name(villa.getVilla_name());
            updatedVilla.setVilla_desc(villa.getVilla_desc());
            updatedVilla.setLocation(villa.getLocation());
            updatedVilla.setAddress(villa.getAddress());
            
            // Save updated location
            Location updatedLocation = locationRepository.save(updatedVilla.getLocation());
            updatedVilla.setLocation(updatedLocation);
            
            return villaRepository.save(updatedVilla);
        }
        
        return null; // Or throw a custom exception for villa not found
    }

    // Delete Villa
    @DeleteMapping("/delete_villa/{id}")
    public String deleteVilla(@PathVariable String id) {
        Optional<Villa> villa = villaRepository.findById(id);
        
        if (villa.isPresent()) {
            villaRepository.delete(villa.get());
            return "Villa with ID " + id + " has been deleted.";
        }
        
        return "Villa not found with ID " + id; // Or throw an exception if you prefer
    }

    @GetMapping("/show_villa")
    public List<Villa> getAllVillas() {
        return villaRepository.findAll();
    }
        // UsersController should be mostly done here
}