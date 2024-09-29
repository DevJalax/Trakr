package com.example.transportation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vehicleId; // Foreign key to Vehicle
    private String updateTime; // Time of status update
    private String newStatus; // New status of the vehicle

    // Getters and Setters
}
