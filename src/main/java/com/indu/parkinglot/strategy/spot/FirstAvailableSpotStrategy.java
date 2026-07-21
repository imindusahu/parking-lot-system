package com.indu.parkinglot.strategy.spot;

import java.util.List;

import com.indu.parkinglot.model.ParkingFloor;
import com.indu.parkinglot.model.ParkingSpot;
import com.indu.parkinglot.model.Vehicle;

public class FirstAvailableSpotStrategy implements SpotSelectionStrategy {

    @Override
    public ParkingSpot selectSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {

        for(ParkingFloor floor : parkingFloors) {
            
            for(ParkingSpot spot : floor.getParkingSpots()) { 
            boolean typeMatches = spot.getSpotType().name().equals(vehicle.getVehicleType().name());

                if(!spot.isOccupied() && typeMatches) {
                    return spot;
                }
            }
        }
        return null;
    }
}