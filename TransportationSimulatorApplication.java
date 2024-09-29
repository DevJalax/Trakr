package com.example.transportation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Enable scheduling support for the application.
public class TransportationSimulatorApplication {

   public static void main(String[] args) {
       SpringApplication.run(TransportationSimulatorApplication.class, args);
   }
}
