package com.example.transportation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // e.g., Train, Bus, Flight, Ship
    private String number; // Vehicle number
    private String status; // e.g., Arrived, Departed, Cancelled
    private String platform; // Arrival/Departure platform
    private String arrivalTime; // 24-hour format
    private String departureTime; // 24-hour format
    private String gpsCoordinates; // Placeholder for GPS data

    // Getters and Setters
}
