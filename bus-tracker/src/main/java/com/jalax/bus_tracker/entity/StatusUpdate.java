package com.jalax.bus_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UPDATES")
public class StatusUpdate {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  @Column(name="VEHICLE_ID")
  private Long vehicleId; // Foreign key to Vehicle
  
  @Column(name="UPDATED_TIME")
  private String updateTime; // Time of status update
  
  @Column(name="NEW_STATUS")
  private String newStatus; // New status of the vehicle

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getVehicleId() {
	return vehicleId;
}

public void setVehicleId(Long vehicleId) {
	this.vehicleId = vehicleId;
}

public String getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}

public String getNewStatus() {
	return newStatus;
}

public void setNewStatus(String newStatus) {
	this.newStatus = newStatus;
}

@Override
public String toString() {
	return "StatusUpdate [id=" + id + ", vehicleId=" + vehicleId + ", updateTime=" + updateTime + ", newStatus="
			+ newStatus + "]";
}
	
}
