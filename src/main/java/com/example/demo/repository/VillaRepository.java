package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Villa;

@Repository
public interface VillaRepository extends MongoRepository<Villa, Object> {
    List<Villa> findByvillaOwnerid(String villaOwnerid);
    List<Villa> findByOccupancyAndlocationNameAndavailableDate(int occupancy,String locationName,LocalDate availableDate);
}
