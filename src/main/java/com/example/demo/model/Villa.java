package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "villas")
public class Villa {

    @Id
    private String id;
    private String villa_name;
    private String villa_desc;
    private String address;
    private String price;
    private int occupancy;
    private LocalDate availableDate;
    private int review_rating;
    private String review_comment;
    private String villaOwnerid;
    private String[] imagePath;
    
    @CreatedDate
    private LocalDateTime createdOn;
    private String locationName;
    @LastModifiedDate
    private LocalDateTime updatedOn;

}