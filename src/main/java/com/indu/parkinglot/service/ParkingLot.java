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
import com.indu.parkinglot.observer.DisplayBoard;
import com.indu.parkinglot.observer.ParkingLotObserver;
import com.indu.parkinglot.strategy.spot.SpotSelectionStrategy;
import com.indu.parkinglot.strategy.spot.FirstAvailableSpotStrategy;
import com.indu.parkinglot.model.SpotType;

public class ParkingLot {

    private List<ParkingSpot> parkingSpots;

    private int ticketCounter = 1;

    private PricingStrategy pricingStrategy;

    private static ParkingLot instance;

    private List<ParkingLotObserver> observers = new ArrayList<>();

    private SpotSelectionStrategy spotStrategy;

    private ParkingLot(int totalSpots) {
        parkingSpots = new ArrayList<>();
        pricingStrategy = new HourlyPricingStrategy();
        spotStrategy = new FirstAvailableSpotStrategy();

        for(int i = 1; i <= totalSpots; i++) {
            if(i == 1 || i == 2) {
                parkingSpots.add(new ParkingSpot(i, SpotType.BIKE));
            }else if( i == 3 || i == 4) {
                parkingSpots.add(new ParkingSpot(i, SpotType.CAR));
            }else {
                parkingSpots.add(new ParkingSpot(i, SpotType.TRUCK));
            }
        }

        observers.add(new DisplayBoard());
    }

    private void notifyObservers() {
        int availableSpots = 0;

        for(ParkingSpot spot : parkingSpots) {
            if(!spot.isOccupied()) {
                availableSpots++;
            }
        }

        for(ParkingLotObserver observer : observers) {
            observer.update(availableSpots);
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
        ParkingSpot spot = spotStrategy.selectSpot(parkingSpots, vehicle);

        if(spot == null) {
            return null;
        }

        spot.parkVehicle(vehicle);

        notifyObservers();

        String ticketId = "T" + ticketCounter++;

        return new Ticket(ticketId, vehicle, spot);
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
        System.out.println("Total Parking Fee : Rs." + fee);
        System.out.println();

        notifyObservers();

    }
}