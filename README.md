# Transportation Simulator

## Summary

- **Search Functionality**: Implemented using Spring Data JPA Specifications to filter vehicles based on multiple criteria, allowing for dynamic and reusable queries.

- **GPS Integration**: Fetches real-time location data from an external GPS API based on the uploaded vehicles' numbers, enabling tracking of vehicle positions.

- **WebSocket Notifications**: Provides real-time updates on vehicle status changes, ensuring users receive instant notifications about important events.

- **Scheduler for GPS Tracking**: A scheduled task that retrieves GPS data every 10 seconds for each vehicle, ensuring up-to-date location information is maintained in the system.

## Features

- Upload vehicle data via Excel files.
- Real-time tracking of vehicles with GPS data.
- Dynamic search capabilities for filtering vehicles.
- WebSocket support for live notifications.

## Getting Started

1. Clone the repository.
2. Set up your environment with the necessary dependencies.
3. Run the application and access the endpoints for vehicle management and tracking.

## Technologies Used

- Spring Boot
- Spring Data JPA
- WebSocket
- Apache POI (for Excel file handling)
- H2 Database (for in-memory storage)
- RESTful API design
