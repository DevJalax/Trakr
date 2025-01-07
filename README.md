# Transportation Simulator

## Summary

- **Search Functionality**: Implemented using Spring Data JPA Specifications to filter vehicles based on multiple criteria, allowing for dynamic and reusable queries.

- **GPS Integration**: Fetches real-time location data from an external GPS API based on the uploaded vehicles' numbers, enabling tracking of vehicle positions.

- **WebSocket Notifications**: Provides real-time updates on vehicle status changes, ensuring users receive instant notifications about important events.

- **Scheduler for GPS Tracking**: A scheduled task that retrieves GPS data every second for each vehicle, ensuring up-to-date location information is maintained in the system.

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

## Diagram

![sample](bus-tracker.png)

## To do in future 

1) Vehicle condition monitoring (sensors)

2) Driver skill judgement using 
a) PIEV model
b) Road marking following (bottom sensors)
c) Sign board following (front sensors)
d) Travel time , arrived time and delays

3) Analysis
a) Highway traffic / condition / alternate route suggestion
b) Traffic density
c) Road capacity per second
d) Intersection roads / Train crossings / Flyovers traffic

## Trivia 

Preferred departure times of Bus : 

- 6:30 AM: Early travelers (overnight or long-distance).
- 9:00 AM: Morning commuters or short/medium-distance travelers.
- 12:30 AM: Mid-day travelers.
- 5:30 PM: Evening commuters.
- 9:30 PM: Overnight travelers.
- weekdays : early mornings or night
- weekend - all slots fill
