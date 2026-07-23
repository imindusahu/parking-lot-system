# 🚗 Parking Lot System

A Java-based Parking Lot System built using **Object-Oriented Programming (OOP)**, **SOLID Principles**, and multiple **Design Patterns**.

This project simulates a real-world parking lot capable of parking different vehicle types, generating parking tickets, calculating parking fees, processing payments, and updating the display board automatically.

---

# Features

- Park and Unpark Vehicles
- Support for Multiple Vehicle Types
  - 🚗 Car
  - 🏍 Bike
  - 🚚 Truck
- Automatic Ticket Generation
- Parking Fee Calculation
- Multiple Parking Spot Selection Strategies
- Multiple Payment Methods
- Payment Receipt Generation
- Live Display Board Updates
- Modular and Extensible Architecture

---

# Tech Stack

- Java 21
- Maven
- Object-Oriented Programming
- SOLID Principles
- Git
- GitHub

---

# Design Patterns Used

| Pattern | Usage |
|----------|-------|
| Factory Pattern | Vehicle Creation |
| Singleton Pattern | Parking Lot Instance |
| Strategy Pattern | Spot Selection & Fee Calculation |
| Observer Pattern | Display Board Updates |
| Command Pattern | Park & Unpark Operations |

---

# Project Structure

```text
src
└── main
    └── java
        └── com
            └── indu
                └── parkinglot
                    ├── command
                    ├── factory
                    ├── model
                    ├── observer
                    ├── payment
                    ├── service
                    ├── strategy
                    └── Main.java
```

---

# Payment Module

Supported payment methods:

- UPI
- Card
- Cash

Payment Status:

- SUCCESS
- FAILED
- PENDING

Each successful payment generates a detailed receipt containing:

- Receipt ID
- Ticket ID
- Vehicle Details
- Spot Number
- Amount
- Payment Method
- Payment Status
- Payment Time

---

# SOLID Principles Applied

- Single Responsibility Principle (SRP)
- Open/Closed Principle (OCP)
- Liskov Substitution Principle (LSP)
- Interface Segregation Principle (ISP)
- Dependency Inversion Principle (DIP)

---

# Sample Output

```text
====== Parking Lot ======

Car parked at Spot: 3
Car Ticket ID: T1

Paid Rs.20.0 using UPI

=========== PAYMENT RECEIPT ===========
Receipt ID        : R1
Ticket ID         : T1
Vehicle Number    : UP32AB1234
Vehicle Type      : CAR
Spot Number       : 3
Amount            : Rs.20.0
Payment Method    : UPI
Status            : SUCCESS
=======================================
```

---

# Future Improvements

- Multi-floor Parking Management
- Reservation System
- Admin Dashboard
- REST API using Spring Boot
- Database Integration (MySQL/PostgreSQL)
- JWT Authentication
- Unit Testing with JUnit
- Docker Support

---

# Author

**Indu Sahu**

- GitHub: https://github.com/imindusahu

---

## License

This project is created for learning Low Level Design (LLD), Java, and Design Patterns.