package com.indu.parkinglot.service;

import java.util.List;
import java.util.ArrayList;

import com.indu.parkinglot.model.ParkingSpot;

public class ParkingLot {

    private List<ParkingSpot> parkingSpots;

    public ParkingLot(int totalSpots) {
        parkingSpots = new ArrayList<>();

        for(int i = 1; i <= totalSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }
}