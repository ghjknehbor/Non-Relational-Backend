package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.VillaRepository;
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private VillaRepository villaRepository;

    @PostMapping("/create_reservation")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PostMapping("/view_user_reservation/{userId}")
    public List<Reservation> ViewUserReservation(@PathVariable String userId) {
        return reservationRepository.findByUserId(userId);
    }
    @PostMapping("/view_owner_reservation/{villaId}")
    public List<Reservation> ViewOwnerReservation(@PathVariable String villaId) {
        return reservationRepository.findByVillaId(villaId);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
        // UsersController should be mostly done here
}