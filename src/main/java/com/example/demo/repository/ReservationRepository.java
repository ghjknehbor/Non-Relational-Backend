package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, Object> {
    
}