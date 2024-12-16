package com.jalax.bus_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jalax.bus_tracker.entity.Vehicle;
import com.jalax.bus_tracker.service.VehicleImportService;
import com.jalax.bus_tracker.service.VehicleService;
import com.jalax.bus_tracker.spec.VehicleSpecification;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	  @Autowired
	  private VehicleService vehicleService;

	   @Autowired
	   private VehicleImportService vehicleImportService;

	   @GetMapping("/")
	   public List<Vehicle> getAllVehicles() {
	       return vehicleService.getAllVehicles();
	   }

	   @PostMapping("/")
	   public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
	       return vehicleService.saveVehicle(vehicle);
	   }

	   @PutMapping("/")
	   public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
	       return vehicleService.updateVehicle(vehicle);
	   }
	
	   @DeleteMapping("/{id}")
	   public void deleteVehicle(@PathVariable Long id) {
	       vehicleService.deleteVehicle(id);
	   }

	   @PostMapping("/upload")
	   public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
	       try {
	           vehicleImportService.importVehiclesFromExcel(file);
	           return ResponseEntity.ok("Vehicles imported successfully!");
	       } catch (Exception e) {
	           return ResponseEntity.status(500).body("Error importing vehicles: " + e.getMessage());
	       }
	   }

	   @GetMapping("/search")
	   public List<Vehicle> searchVehicles(
	           @RequestParam(required = false) String type,
	           @RequestParam(required = false) String status,
	           @RequestParam(required = false) String platform) {

	       Specification<Vehicle> spec = Specification.where(VehicleSpecification.hasType(type))
	               .and(VehicleSpecification.hasStatus(status))
	               .and(VehicleSpecification.hasPlatform(platform));
	       
	       return vehicleService.searchVehicles(spec);
	   }
}
