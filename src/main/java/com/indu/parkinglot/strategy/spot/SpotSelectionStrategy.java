package com.indu.parkinglot.strategy.spot;

import java.util.List;

import com.indu.parkinglot.model.ParkingSpot;
import com.indu.parkinglot.model.Vehicle;

public interface SpotSelectionStrategy {
    
    ParkingSpot selectSpot(List<ParkingSpot> parkingSpots, Vehicle vehicle);
}