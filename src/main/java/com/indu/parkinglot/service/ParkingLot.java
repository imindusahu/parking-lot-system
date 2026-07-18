package com.indu.parkinglot.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;

import com.indu.parkinglot.model.ParkingSpot;
import com.indu.parkinglot.model.Ticket;
import com.indu.parkinglot.model.Vehicle;

public class ParkingLot {

    private List<ParkingSpot> parkingSpots;
    private int ticketCounter = 1;

    public ParkingLot(int totalSpots) {
        parkingSpots = new ArrayList<>();

        for(int i = 1; i <= totalSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }

    private double calculateFee(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();

        Duration duration = Duration.between(entryTime, exitTime);

        long minutes = duration.toMinutes();
        long hours = (minutes + 59) / 60;
        double fee = hours * 20;

        System.out.println("Entry Time : " + entryTime);
        System.out.println("Exit Time  : " + exitTime);
        System.out.println("Minutes    : " + minutes);
        System.out.println("Hours.     : " + hours);
        System.out.println("Fee        : " + fee);

        return fee;
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

    public void unparkVehicle(Ticket ticket) {
        Vehicle vehicle = ticket.getVehicle();
        ParkingSpot spot = findParkingSpot(vehicle);

        if(spot == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        double fee = calculateFee(ticket);

        spot.removeVehicle();

        System.out.println("Entry Time : " + entryTime);
        System.out.println("Exit Time  : " + exitTime);
        System.out.println("Minutes    : " + minutes);
        System.out.println("Hours.     : " + hours);
        System.out.println("Fee        : " + fee);

    }
}