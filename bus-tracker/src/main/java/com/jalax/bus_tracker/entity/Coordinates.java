package com.jalax.bus_tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Coordinates")
public class Coordinates {

	@Column(name="LATITUDE")
	private Double Latitude;
	
	@Column(name="LONGITUDE")
	private Double Longitude;
	
	public Double getLatitude() {
		return Latitude;
	}
	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}
	public Double getLongitude() {
		return Longitude;
	}
	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Coordinates [Latitude=" + Latitude + ", Longitude=" + Longitude + "]";
	}
	
	public Coordinates(double latitude2, double longitude2) {
		this.Latitude = latitude2;
		this.Longitude = longitude2;
	}
	
}
