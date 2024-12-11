package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Villa;
import com.example.demo.repository.VillaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/villa")
public class VillaController {
    @Autowired
    private VillaRepository villaRepository;
    @PostMapping("/create_villa")

    public Villa createVilla(@RequestBody Villa villa) {
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
            updatedVilla.setAddress(villa.getAddress());
            updatedVilla.setLocationName(villa.getLocationName());
            updatedVilla.setAvailableDate(villa.getAvailableDate());
            updatedVilla.setOccupancy(villa.getOccupancy());
            updatedVilla.setPrice(villa.getPrice());
            updatedVilla.setImagePath(villa.getImagePath());
            
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

    @PostMapping("/view_villa/{villaOwnerid}")
    public List<Villa> ViewOwnerVilla(@PathVariable String villaOwnerid) {
        return villaRepository.findByvillaOwnerid(villaOwnerid);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Villa>> searchVilla(@RequestBody SearchClass searchInput) {
    // Use the repository to filter villas by location, date, and occupancy
    List<Villa> filteredVillas = villaRepository.findByLocationNameAndAvailableDateAndOccupancy(
        searchInput.getEntered_locationName(),
        searchInput.getEntered_availableDate(),
        searchInput.getEntered_occupancy()
    );

    if (filteredVillas.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
    }

    // Return filtered villas with 200 OK
    return ResponseEntity.ok(filteredVillas);
}


@Data
@AllArgsConstructor
@NoArgsConstructor
public static class SearchClass {
    private String entered_locationName;
    private String entered_availableDate;
    private String entered_occupancy;
}
}