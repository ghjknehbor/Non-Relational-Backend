package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, Object> {
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByVillaId(String villaId);
}