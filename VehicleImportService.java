package com.example.transportation.service;

import com.example.transportation.model.Vehicle;
import com.example.transportation.repository.VehicleRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleImportService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private GpsService gpsService; // Inject the GPS service

    public void importVehiclesFromExcel(MultipartFile file) throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row
                Row row = sheet.getRow(i);
                Vehicle vehicle = new Vehicle();
                vehicle.setType(row.getCell(0).getStringCellValue());
                vehicle.setNumber(row.getCell(1).getStringCellValue());
                vehicle.setStatus(row.getCell(2).getStringCellValue());
                vehicle.setPlatform(row.getCell(3).getStringCellValue());
                vehicle.setArrivalTime(row.getCell(4).getStringCellValue());
                vehicle.setDepartureTime(row.getCell(5).getStringCellValue());
                vehicles.add(vehicle);
            }
        }
        vehicleRepository.saveAll(vehicles);
        
        // Start tracking vehicles after upload
        trackVehiclesInRealTime(vehicles);
    }

    private void trackVehiclesInRealTime(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            String gpsData = gpsService.fetchGpsData(vehicle.getNumber());
            vehicle.setGpsCoordinates(gpsData);
            vehicleRepository.save(vehicle); // Update the vehicle with GPS data.
        }
    }
}
