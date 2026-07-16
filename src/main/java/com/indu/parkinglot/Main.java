package com.indu.parkinglot;

import com.indu.parkinglot.model.Bike;
import com.indu.parkinglot.model.Car;
import com.indu.parkinglot.model.Truck;
import com.indu.parkinglot.service.ParkingLot;

public class Main 
{
    public static void main( String[] args )
    {
        Car car = new Car("UP32AB1234");
        Bike bike = new Bike("UP32XY5678");
        Truck truck = new Truck("UP78PQ9999");
        ParkingLot parkingLot = new ParkingLot(5);

        System.out.println( "===== Vehicles =====");

        System.out.println(car.getVehicleNumber());
        System.out.println(car.getVehicleType());
        System.out.println();

        System.out.println(bike.getVehicleNumber());
        System.out.println(bike.getVehicleType());
        System.out.println();

        System.out.println(truck.getVehicleNumber());
        System.out.println(truck.getVehicleType());
    
    }
}
