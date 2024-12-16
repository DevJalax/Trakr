package com.jalax.bus_tracker.scheduler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.RouteMatcher;
import org.springframework.util.RouteMatcher.Route;

import com.jalax.bus_tracker.entity.Coordinates;
import com.jalax.bus_tracker.entity.Vehicle;
import com.jalax.bus_tracker.service.GpsService;
import com.jalax.bus_tracker.service.NotificationService;
import com.jalax.bus_tracker.service.VehicleService;

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
            checkVehicleStatus(vehicle);
            notificationService.sendNotification("Updated status for vehicle: " + vehicle.getNumber());
            vehicleService.updateVehicle(vehicle);
        }
    }
	
    @Scheduled(fixedRate = 1000) // Every second for GPS tracking
    public void trackGpsData() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        
        for (Vehicle vehicle : vehicles) {
            // Fetch current GPS coordinates for the vehicle
            String gpsData = gpsService.fetchGpsData(vehicle.getNumber());
            vehicle.setGpsCoordinates(gpsData);

            // Check if the vehicle has arrived at its destination
            String status = checkVehicleArrivalStatus(vehicle, gpsData);
            vehicle.setStatus(status);

            // Notify about the GPS update
            notificationService.sendNotification("GPS updated for vehicle: " + vehicle.getNumber() + " - " + gpsData);

            // Update the vehicle details in the database
            vehicleService.updateVehicle(vehicle);
            checkVehicleStatus(vehicle); // Check and update status
        }
    }
    
    private void checkVehicleStatus(Vehicle vehicle) {
        RouteMatcher.Route assignedRoute = vehicleService.getRouteById(vehicle.getAssignedRouteId());
        Coordinates currentCoords = parseCoordinates(vehicle.getCurrentCoordinates());

        List<Coordinates> routeCoordinates = assignedRoute.getRouteSegments()
        		.stream()
        		.map(segment -> new Coordinates(segment.startLatitude(), segment.startLongitude()))
        		.collect(Collectors.toList());
        		
        
        for (Coordinates routeCoord : routeCoordinates ) {
            double distance = calculateDistance(currentCoords, routeCoord);

            // Check if vehicle deviates from the route (e.g., 500 meters tolerance)
            if (distance > 0.5) {
                vehicle.setStatus("In Transit");
            } else {
                vehicle.setStatus("On Time");
            }
        }

        vehicleService.updateVehicle(vehicle);
    }

    /**
     * Checks if the vehicle's departure coordinates match its arrival coordinates
     * and updates the status accordingly.
     */
    private String checkVehicleArrivalStatus(Vehicle vehicle, String currentGpsCoordinates) {
        String departureCoordinates = vehicle.getDepartureCoordinates(); // E.g., "12.971598,77.594566"
        String arrivalCoordinates = vehicle.getArrivalCoordinates();     // E.g., "13.082680,80.270718"

        if (currentGpsCoordinates.equalsIgnoreCase(arrivalCoordinates)) {
            checkVehicleStatus(vehicle); 
        }

        // If the current location doesn't match the destination, keep the original status
        return vehicle.getStatus();
    }
    
 // Method to calculate distance between two coordinates (Haversine formula)
    private double calculateDistance(Coordinates coord1, Coordinates coord2) {
        double earthRadius = 6371; // Radius of Earth in kilometers
        double dLat = Math.toRadians(coord2.getLatitude() - coord1.getLatitude());
        double dLon = Math.toRadians(coord2.getLongitude() - coord1.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(coord1.getLatitude())) * Math.cos(Math.toRadians(coord2.getLatitude())) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c; // Distance in kilometers
    }
    
 // Helper method to parse GPS coordinates from string
    private Coordinates parseCoordinates(String gpsData) {
        String[] parts = gpsData.split(", ");
        double latitude = Double.parseDouble(parts[0].split(": ")[1]);
        double longitude = Double.parseDouble(parts[1].split(": ")[1]);
        return new Coordinates(latitude, longitude);
     }
    
 // Method to check for bus breakdown (stationary for too long)
   @Scheduled(fixedRate = 600000)
   public void checkForBusBreakdown(Vehicle vehicle) {
       long lastUpdate = vehicle.getLastUpdateTimeStamp();
       long currentTime = System.currentTimeMillis();

       // If the vehicle hasn't updated for more than 30 minutes
       if ((currentTime - lastUpdate) > 1800000) { // 30 minutes without movement
           notificationService.sendNotification("Vehicle " + vehicle.getNumber() + " is not moving. Possible Breakdown.");
           assignNewBus(vehicle);
       }
   }
   
// Method to assign a new bus on the same route if the bus is not moving (e.g., breakdown)
private void assignNewBus(Vehicle vehicle) {
    List<Vehicle> availableBuses = vehicleService.getAvailableBuses(vehicle.getAssignedRouteId());

    // Check if an alternate bus is available and not on any other route
    Vehicle newBus = availableBuses.stream()
            .filter(bus -> bus.getStatus().equals("On Time"))
            .findFirst().orElse(null);

    if (newBus != null) {
        vehicleService.assignNewBus(vehicle, newBus);
        notificationService.sendNotification("New bus assigned: " + newBus.getNumber());
    } else {
        notificationService.sendNotification("No available bus for re-assignment.");
    }
  }
}
