package com.jalax.bus_tracker.spec;

import org.springframework.data.jpa.domain.Specification;

import com.jalax.bus_tracker.entity.Vehicle;

public class VehicleSpecification {

	public static Specification<Vehicle> hasType(String type) {
	       return (vehicle, query, criteriaBuilder) -> 
	           type == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(vehicle.get("type"), type);
	   }

	   public static Specification<Vehicle> hasStatus(String status) {
	       return (vehicle, query, criteriaBuilder) -> 
	           status == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(vehicle.get("status"), status);
	   }

	   public static Specification<Vehicle> hasPlatform(String platform) {
	       return (vehicle, query, criteriaBuilder) -> 
	           platform == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(vehicle.get("platform"), platform);
	   }
}
