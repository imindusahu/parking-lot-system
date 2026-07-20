package com.indu.parkinglot.model;

public class ParkingSpot {
    
    private int spotNumber;
    private SpotType spotType;
    private Vehicle parkedVehicle;
    private boolean occupied;

    public ParkingSpot(int spotNumber, SpotType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.parkedVehicle = null;
        this.occupied = false;
    }

    public void parkVehicle(Vehicle vehicle) {
        
        if(occupied) {
            System.out.println("Parking Spot is already occupied.");
            return;
        }
        
        this.parkedVehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.occupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean isOccupied() {
        return occupied;
    }
}