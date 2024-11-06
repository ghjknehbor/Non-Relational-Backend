package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Villa_Owner;

@Repository
public interface Villa_OwnerRepository extends MongoRepository<Villa_Owner, Object> {
    
}