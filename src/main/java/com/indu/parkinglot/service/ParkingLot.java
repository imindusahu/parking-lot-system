package com.indu.parkinglot.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;

import com.indu.parkinglot.model.ParkingSpot;
import com.indu.parkinglot.model.Ticket;
import com.indu.parkinglot.model.Vehicle;
import com.indu.parkinglot.strategy.PricingStrategy;
import com.indu.parkinglot.strategy.HourlyPricingStrategy;

public class ParkingLot {

    private List<ParkingSpot> parkingSpots;

    private int ticketCounter = 1;

    private PricingStrategy pricingStrategy;

    private static ParkingLot instance;

    private ParkingLot(int totalSpots) {
        parkingSpots = new ArrayList<>();
        pricingStrategy = new HourlyPricingStrategy();

        for(int i = 1; i <= totalSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }

    public static ParkingLot getInstance(int totalSpots) {
        if(instance == null) {
            instance = new ParkingLot(totalSpots);
        }

        return instance;
    }

    private ParkingSpot findParkingSpot(Vehicle vehicle) {
        for(ParkingSpot spot : parkingSpots) {
           if(spot.getParkedVehicle() != null) {
                if (spot.getParkedVehicle() == vehicle) 
                    return spot;
            }
        }
        return null;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for(ParkingSpot spot : parkingSpots) {
            if(!spot.isOccupied()) {
                spot.parkVehicle(vehicle);

                String ticketId = "T" + ticketCounter;
                ticketCounter++;

                Ticket ticket = new Ticket(ticketId, vehicle, spot);
                return ticket;
            }
        }
        return null;
    }

    private double calculateFee(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();

        Duration duration = Duration.between(entryTime, exitTime);

        long minutes = duration.toMinutes();
        long hours = (minutes + 59) / 60;

        return pricingStrategy.calculateFee(hours);
    }

    public void unparkVehicle(Ticket ticket) {
        Vehicle vehicle = ticket.getVehicle();
        ParkingSpot spot = findParkingSpot(vehicle);

        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();

        Duration duration = Duration.between(entryTime, exitTime);

        long minutes = duration.toMinutes();
        long hours = (minutes + 59) / 60;
        double fee = calculateFee(ticket);

        if(spot == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        spot.removeVehicle();

        System.out.println("Entry Time        : " + entryTime);
        System.out.println("Exit Time         : " + exitTime);
        System.out.println("Minutes           : " + minutes);
        System.out.println("Hours.            : " + hours);
        System.out.println("Total Parking Fee : Rs." + fee);

    }
}