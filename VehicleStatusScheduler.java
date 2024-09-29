package com.example.transportation.scheduler;

import com.example.transportation.model.Vehicle;
import com.example.transportation.service.VehicleService;
import com.example.transportation.service.GpsService;
import com.example.transportation.websocket.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleStatusScheduler {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private GpsService gpsService; // Inject the GPS service

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 3600000) // Every hour in milliseconds for status updates
    public void updateVehicleStatuses() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        
        for (Vehicle vehicle : vehicles) {
            String currentStatus = checkVehicleStatus(vehicle);
            vehicle.setStatus(currentStatus);
            notificationService.sendNotification("Updated status for vehicle: " + vehicle.getNumber() + " - " + currentStatus);
            vehicleService.updateVehicle(vehicle);
        }
    }

    @Scheduled(fixedRate = 10000) // Every 10 seconds in milliseconds for GPS tracking
    public void trackGpsData() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        
        for (Vehicle vehicle : vehicles) {
            String gpsData = gpsService.fetchGpsData(vehicle.getNumber());
            vehicle.setGpsCoordinates(gpsData);
            notificationService.sendNotification("GPS updated for vehicle: " + vehicle.getNumber() + " - " + gpsData);
            vehicleService.updateVehicle(vehicle); // Update the vehicle with new GPS data.
        }
    }

    private String checkVehicleStatus(Vehicle vehicle) {
        if ("Arrived".equalsIgnoreCase(vehicle.getStatus())) {
            return "Departed"; 
        } else if ("Departed".equalsIgnoreCase(vehicle.getStatus())) {
            return "Cancelled"; 
        } else {
            return "On Time"; 
        }
    }
}
