package com.jalax.bus_tracker.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="GPS_DATA")
public class TeltonikaGpsData {

	 @Column(name="BUS_NO")
	 private String busNumber;
     
	 @Column(name="LATITUDE")
	 private double latitude;
     
	 @Column(name="LONGITUDE")
	 private double longitude;
     
	 @Column(name="TIMESTAMP")
	 private Date timestamp;

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TeltonikaGpsData [busNumber=" + busNumber + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", timestamp=" + timestamp + "]";
	}
	
}
