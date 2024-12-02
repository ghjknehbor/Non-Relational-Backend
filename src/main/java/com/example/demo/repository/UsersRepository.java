package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, Object> {
    Users findByEmailAndPassword(String email, String password);
    Users findByEmail(String email);
}