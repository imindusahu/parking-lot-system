package com.indu.parkinglot.strategy.spot;

import java.util.List;

import com.indu.parkinglot.model.ParkingSpot;
import com.indu.parkinglot.model.Vehicle;

public class FirstAvailableSpotStrategy implements SpotSelectionStrategy {

    @Override
    public ParkingSpot selectSpot(List<ParkingSpot> parkingSpots, Vehicle vehicle) {

        for(ParkingSpot spot : parkingSpots) {
            if(!spot.isOccupied()) {
                return spot;
            }
        }
        return null;
    }
}