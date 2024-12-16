package com.jalax.bus_tracker.gps;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.jalax.bus_tracker.entity.TeltonikaGpsData;

public class TeltonikaServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Listening on port " + PORT + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to device: " + clientSocket.getInetAddress());

                // Handle incoming data in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private static void handleClient(Socket clientSocket) {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {

            // Read binary data from Teltonika device
            byte[] data = input.readNBytes(1024); // Adjust buffer size if needed
            System.out.println("Received data: " + bytesToHex(data));

            // Decode Teltonika AVL data (implement decoding logic below)
            TeltonikaGpsData teltonikaData = decodeTeltonikaData(data);

            // Save the decoded data to MySQL
            saveToDatabase(teltonikaData);

            // Send ACK response to the device
            output.write(new byte[]{0x01}); // ACK
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static TeltonikaGpsData decodeTeltonikaData(byte[] data) {
        // Teltonika AVL decoding logic here
        // For example, extract bus number, latitude, longitude, timestamp, etc.
    	TeltonikaGpsData teltonikaData = new TeltonikaGpsData();
        teltonikaData.setBusNumber("Bus123");  // Replace with actual decoding logic
        teltonikaData.setLatitude(12.971598); // Replace with actual decoding logic
        teltonikaData.setLongitude(77.594566); // Replace with actual decoding logic
        teltonikaData.setTimestamp(new java.util.Date()); // Replace with actual decoding logic
        return teltonikaData;
    }

    private static void saveToDatabase(TeltonikaGpsData data) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "root";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO bus_location (bus_number, latitude, longitude, timestamp) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, data.getBusNumber());
                stmt.setDouble(2, data.getLatitude());
                stmt.setDouble(3, data.getLongitude());
                stmt.setTimestamp(4, new Timestamp(data.getTimestamp().getTime()));
                stmt.executeUpdate();
                System.out.println("Data inserted into MySQL.");
            }
        }
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } 
}

