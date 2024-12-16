package com.jalax.bus_tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.RouteMatcher;
import org.springframework.util.RouteMatcher.Route;

import com.jalax.bus_tracker.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

	 @Query("SELECT org.springframework.util.RouteMatcher.Route.of(e.coordinates) " +
	           "FROM VEHICLE_INFO e WHERE e.assignedRouteId = :assignedRouteId")
	RouteMatcher.Route findRouteByAssignedRouteId(String assignedRouteId);
}
