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
import com.indu.parkinglot.model.ParkingFloor;
import com.indu.parkinglot.payment.PaymentStrategy;
import com.indu.parkinglot.payment.CashPayment;

public class ParkingLot {

    private List<ParkingFloor> parkingFloors;

    private int ticketCounter = 1;

    private PricingStrategy pricingStrategy;

    private PaymentStrategy paymentStrategy;

    private static ParkingLot instance;

    private List<ParkingLotObserver> observers = new ArrayList<>();

    private SpotSelectionStrategy spotStrategy;

    private ParkingLot(int totalSpots) {
        parkingFloors = new ArrayList<>();
        pricingStrategy = new HourlyPricingStrategy();
        paymentStrategy = new CashPayment();
        spotStrategy = new FirstAvailableSpotStrategy();

        ParkingFloor floor1 = new ParkingFloor(1);
        ParkingFloor floor2 = new ParkingFloor(2);

        //Add spots to Floor1
        floor1.addSpot(new ParkingSpot(1, SpotType.BIKE));
        floor1.addSpot(new ParkingSpot(2, SpotType.BIKE));
        floor1.addSpot(new ParkingSpot(3, SpotType.CAR));

        //Add spots to Floor2
        floor2.addSpot(new ParkingSpot(4, SpotType.CAR));
        floor2.addSpot(new ParkingSpot(5, SpotType.TRUCK));

        parkingFloors.add(floor1);
        parkingFloors.add(floor2);

        observers.add(new DisplayBoard());
    }

    private void notifyObservers() {
        int availableSpots = 0;

        for(ParkingFloor floor : parkingFloors) {
            for(ParkingSpot spot : floor.getParkingSpots()) {
                    if(!spot.isOccupied()) {
                    availableSpots++;
                }
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

    public void setSpotSelectionStrategy(SpotSelectionStrategy strategy) {
        this.spotStrategy = strategy;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    private ParkingSpot findParkingSpot(Vehicle vehicle) {
        for(ParkingFloor floor : parkingFloors) {
            for(ParkingSpot spot : floor.getParkingSpots()) {
                if(spot.getParkedVehicle() != null && spot.getParkedVehicle() == vehicle) {
                        return spot;
                }
            }
        }
        return null;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = spotStrategy.selectSpot(parkingFloors, vehicle);

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

        paymentStrategy.pay(fee);

        System.out.println("Entry Time        : " + entryTime);
        System.out.println("Exit Time         : " + exitTime);
        System.out.println("Total Parking Fee : Rs." + fee);
        System.out.println();

        notifyObservers();

    }
}