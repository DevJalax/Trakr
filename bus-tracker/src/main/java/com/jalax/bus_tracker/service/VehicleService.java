package com.jalax.bus_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.RouteMatcher;
import org.springframework.util.RouteMatcher.Route;

import com.jalax.bus_tracker.entity.Vehicle;
import com.jalax.bus_tracker.repo.VehicleRepository;

@Service
public class VehicleService {

	    @Autowired
	    private VehicleRepository vehicleRepository;

	    public List<Vehicle> getAllVehicles() {
	        return vehicleRepository.findAll();
	    }

	    public Vehicle saveVehicle(Vehicle vehicle) {
	        return vehicleRepository.save(vehicle);
	    }

	    public Vehicle updateVehicle(Vehicle vehicle) {
	        return vehicleRepository.save(vehicle);
	    }

	    public void deleteVehicle(Long id) {
	        vehicleRepository.deleteById(id);
	    }

	    public List<Vehicle> searchVehicles(Specification<Vehicle> spec) {
	        return vehicleRepository.findAll(spec);
	    }

		public RouteMatcher.Route getRouteById(String assignedRouteId) {
			return vehicleRepository.findRouteByAssignedRouteId(assignedRouteId);
		}

		public List<Vehicle> getAvailableBuses(String assignedRouteId) {
			// TODO Auto-generated method stub
			return null;
		}

		public void assignNewBus(Vehicle vehicle, Vehicle newBus) {
			// TODO Auto-generated method stub
			
		}

}
