import java.io.*;
import java.net.*;
import java.sql.*;

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
            TeltonikaData teltonikaData = decodeTeltonikaData(data);

            // Save the decoded data to MySQL
            saveToDatabase(teltonikaData);

            // Send ACK response to the device
            output.write(new byte[]{0x01}); // ACK
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static TeltonikaData decodeTeltonikaData(byte[] data) {
        // Teltonika AVL decoding logic here
        // For example, extract bus number, latitude, longitude, timestamp, etc.
        TeltonikaData teltonikaData = new TeltonikaData();
        teltonikaData.setBusNumber("Bus123");  // Replace with actual decoding logic
        teltonikaData.setLatitude(12.971598); // Replace with actual decoding logic
        teltonikaData.setLongitude(77.594566); // Replace with actual decoding logic
        teltonikaData.setTimestamp(new java.util.Date()); // Replace with actual decoding logic
        return teltonikaData;
    }

    private static void saveToDatabase(TeltonikaData data) throws SQLException {
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

    // TeltonikaData class to hold the decoded data
    static class TeltonikaData {
        private String busNumber;
        private double latitude;
        private double longitude;
        private java.util.Date timestamp;

        // Getters and Setters
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

        public java.util.Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(java.util.Date timestamp) {
            this.timestamp = timestamp;
        }
    }
}
