package com.example.transportation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GpsService {

   @Autowired
   private RestTemplate restTemplate;

   public String fetchGpsData(String vehicleNumber) {
       // Mock URL for the GPS API. Replace with actual API endpoint.
       String url = "https://api.example.com/gps?vehicleNumber=" + vehicleNumber;

       try {
           // Make an API call to fetch GPS data (mock implementation)
           return restTemplate.getForObject(url, String.class);
       } catch (Exception e) {
           e.printStackTrace();
           return "GPS data not available"; // Handle error appropriately.
       }
   }
}
