package com.jalax.bus_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="VEHICLE_INFO")
public class Vehicle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="VEHICLE_TYPE")
    private String type; // e.g., Train, Bus, Flight, Ship
    
	@Column(name="VEHICLE_NO")
    private String number; // Vehicle number
    
	@Column(name="VEHICLE_STATUS")
    private String status; // e.g., Arrived, Departed, Cancelled
    
	@Column(name="VEHICLE_PLATOFORM")
    private String platform; // Arrival/Departure platform
    
	@Column(name="VEHICLE_ARRIVAL_TIME")
    private String arrivalTime; // 24-hour format
    
	@Column(name="VEHICLE_DEPARTURE_TIME")
    private String departureTime; // 24-hour format
    
	@Column(name="VEHICLE_LOCATION")
    private String gpsCoordinates; // Placeholder for GPS data
    
	@Column(name="VEHICLE_DEPARTURE_LOCATION")
    private String DepartureCoordinates;
    
	@Column(name="VEHICLE_ARRIVAL_LOCATIOM")
    private String ArrivalCoordinates;
    
	@Column(name="VEHICLE_ASSIGNED_ROUTE_ID")
    private String AssignedRouteId;
    
	@Column(name="VEHICLE_CURRENT_LOCATION")
    private String CurrentCoordinates;
    
	@Column(name="VEHICLE_LAST_UPDATE_TIME")
    private Long LastUpdateTimeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getGpsCoordinates() {
		return gpsCoordinates;
	}

	public void setGpsCoordinates(String gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

	public String getDepartureCoordinates() {
		return DepartureCoordinates;
	}

	public void setDepartureCoordinates(String departureCoordinates) {
		DepartureCoordinates = departureCoordinates;
	}

	public String getArrivalCoordinates() {
		return ArrivalCoordinates;
	}

	public void setArrivalCoordinates(String arrivalCoordinates) {
		ArrivalCoordinates = arrivalCoordinates;
	}

	public String getAssignedRouteId() {
		return AssignedRouteId;
	}

	public void setAssignedRouteId(String assignedRouteId) {
		AssignedRouteId = assignedRouteId;
	}

	public String getCurrentCoordinates() {
		return CurrentCoordinates;
	}

	public void setCurrentCoordinates(String currentCoordinates) {
		CurrentCoordinates = currentCoordinates;
	}

	public Long getLastUpdateTimeStamp() {
		return LastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(Long lastUpdateTimeStamp) {
		LastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", number=" + number + ", status=" + status + ", platform="
				+ platform + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", gpsCoordinates="
				+ gpsCoordinates + ", DepartureCoordinates=" + DepartureCoordinates + ", ArrivalCoordinates="
				+ ArrivalCoordinates + ", AssignedRouteId=" + AssignedRouteId + ", CurrentCoordinates="
				+ CurrentCoordinates + ", LastUpdateTimeStamp=" + LastUpdateTimeStamp + "]";
	}
	
}
