package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.model.Villa;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.VillaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        String verificationCode = generateVerificationCode();
        reservation.setVerificationCode(verificationCode);
        return reservationRepository.save(reservation);
    }
    private String generateVerificationCode() {
    int length = 10; 
    Random random = new Random();
    StringBuilder code = new StringBuilder();

    for (int i = 0; i < length; i++) {
        int digit = random.nextInt(10); 
        code.append(digit);
    }

    return code.toString();
}   
    @PostMapping("/verifyReview")
    public Reservation createReview(@RequestBody VerifyReview verifyReview) {
        Reservation reservation = reservationRepository.findByVerificationCode(verifyReview.getVerification_code());
        if (reservation == null) {
            throw new RuntimeException("Reservation not found with the provided verification code, scammer!!.");
        }
        reservation.setUserRate(verifyReview.getUser_rate());
        Optional<Villa> villa = villaRepository.findById(reservation.getVillaId());
        Villa villasave = villa.get();
        villasave.setReview_rating(verifyReview.getUser_rate());
        villaRepository.save(villasave);
        return reservationRepository.save(reservation);
    }

    @PostMapping("/view_user_reservation")
    public List<Reservation> ViewUserReservation(@RequestBody userviewreservation UserRequest) {
        return reservationRepository.findByUserId(UserRequest.getUserId());
    }
    @PostMapping("/view_owner_reservation")
    public List<Reservation> ViewOwnerReservation(@RequestBody ownerviewreservation ownerRequest) {
        return reservationRepository.findByVillaId(ownerRequest.getVillaId());
    }
    @GetMapping("/view_reservations")
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
@Data
@AllArgsConstructor
@NoArgsConstructor
public static class VerifyReview {
    private String verification_code;
    private int user_rate;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public static class userviewreservation {
    private String userId;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public static class ownerviewreservation {
    private String villaId;
}

}