# Trakr Bus Booking System

## 1. Bus Booking  
A seamless platform for booking buses, offering flexible seating options, amenities, and dynamic pricing based on demand.

---

## 2. Dynamic Bus Tracking  
- Utilizes **GPS API** and **Google Maps API** for real-time tracking of buses.
- Provides live updates on:
  - Current bus location.
  - Pitstops and stop updates dynamically calculated based on journey time and route.

---

## 3. Cancellation Policy and Refund Engine  
- Flexible cancellation policies with automated refund processing based on booking terms.
- Instant notifications on refund status.

---

## 4. Machine Learning (ML)

### I. Dynamic Pricing  
- Adjusts ticket prices dynamically based on:
  - **Bus type**: Sleeper, Seater, AC/Non-AC, Primo.
  - **Seat demand**: High-demand seats priced higher.

### II. Driver Skill Analysis  
- **Metrics for Evaluation**:
  - **a) PIEV Model**: Qualitative testing during driver onboarding.
  - **b) Travel Time Analysis**:
    - Formula:  
      `Driver Rating = (D * wD) + (A * wA) + (E * wE)`
      - `D`: Departure Punctuality Score (0–5)
      - `A`: Arrival Punctuality Score (0–5)
      - `E`: En Route Behavior Score (Delays) (0–5)
      - `wD`, `wA`, `wE`: Weights (e.g., 0.3, 0.5, 0.2).

- **Final Rating**:
  - Average of **PIEV Model** score and **Travel Time Analysis** score.
  - Retain drivers with ratings **≥ 4.0**.

---

## 5. Jira Integration for Customer Support  
- Seamless **Jira integration** to log customer complaints and track resolution status.

---

## 6. Automatic Next Bus Scheduling  
- In case of a bus breakdown:
  - Alert nearest bus stops requiring replacement buses.
  - Automatically schedule the next bus and notify passengers.

---

## 7. GPS API for Real-Time Bus Tracking  
- Tracks bus location and displays live updates on passenger apps.

---

## 8. IoT Features  

### a) Vehicle Condition Monitoring  
- Tracks:
  - Fuel levels.
  - Engine health.
  - Battery status.
  - Lights condition.
- Alerts drivers to maintenance needs.

### b) Real-Time Analysis  
- **i)** Highway traffic and alternate route suggestions.  
- **ii)** Traffic density monitoring.  
- **iii)** Road capacity per second analysis.  
- **iv)** Road marking adherence using bottom sensors.  
- **v)** Signboard following using front sensors.  
- **vi)** Intersection, train crossing, and flyover traffic monitoring.

---

## 9. Driver Management  
- **Driver Onboarding and De-boarding** processes.  
- **Salary Management**:
  - **Base Pay**: Fixed monthly pay.  
  - **Incentives**: Based on the number of buses driven/month.

---

## Bus Types  

### 1) Based on Seating  
- Sleeper  
- Seater  

### 2) Based on Amenities  
- Non-AC  
- AC  
- Primo (includes Wi-Fi, restrooms, TV per seat, charging points).

---

## Pit Stop Calculation and Location  

### I. Pitstop Rules  
- **< 200 KM**: 1 stop.  
- **200–400 KM**: 1–2 stops.  
- **> 500 KM**: 2–3 stops.

### II. Dynamic Pitstop Calculation  
- Calculates potential pitstops based on:
  - Bus location.
  - Time into the journey.  
- Filters pitstops based on proximity and amenities (e.g., food, restrooms).  
- Adjusts recommendations dynamically using live traffic and weather updates.

---

## Time Slots  
- **T1**: 6:30 AM  
- **T2**: 9:00 AM  
- **T3**: 12:30 PM  
- **T4**: 5:30 PM  
- **T5**: 9:30 PM  

---

## Luggage Policy  
- **10 KG/passenger** maximum.

---

## API Integration  

1. **Google Maps API**  
   - Route mapping and distance calculations.  

2. **OpenWeatherMap API**  
   - Provides live weather conditions.  

3. **Yelp/Zomato API**  
   - Displays pitstop details, including restaurants.  

4. **GPS API**  
   - Real-time bus tracking.

5. **HRMS Connect**  
   - Manages driver salaries and HR processes.


## GPS API diagram :

![sample](bus-tracker.png)
