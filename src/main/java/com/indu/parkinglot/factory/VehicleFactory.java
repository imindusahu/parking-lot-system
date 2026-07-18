package com.indu.parkinglot.factory;

import com.indu.parkinglot.model.*;

public class VehicleFactory {
    public Vehicle createVehicle(String type, String number) {
        switch(type.toUpperCase()) {
            case "CAR":
                return new Car(number);

            case "BIKE":
                return new Bike(number);

            case "TRUCK":
                return new Truck(number);

            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}