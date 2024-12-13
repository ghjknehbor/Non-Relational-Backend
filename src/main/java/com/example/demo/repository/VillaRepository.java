package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Villa;


@Repository
public interface VillaRepository extends MongoRepository<Villa, Object> {
    List<Villa> findByvillaOwnerid(String villaOwnerid);
    Optional<Villa> findById(String id);
    List<Villa> findByLocationNameAndAvailableDateAndOccupancy(String LocationName,String availableDate,String occupancy);
}
