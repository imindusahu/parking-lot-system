package com.indu.parkinglot;

import com.indu.parkinglot.model.Bike;
import com.indu.parkinglot.model.Car;
import com.indu.parkinglot.model.Truck;
import com.indu.parkinglot.service.ParkingLot;
import com.indu.parkinglot.model.Ticket;

public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        Car car = new Car("UP32AB1234");
        Bike bike = new Bike("UP32XY5678");
        Truck truck = new Truck("UP78PQ9999");

        ParkingLot parkingLot = new ParkingLot(5);

        System.out.println("====== Parking Lot ======");

        //Park vehicles
        Ticket carTicket = parkingLot.parkVehicle(car);
        Ticket bikeTicket = parkingLot.parkVehicle(bike);
        Ticket truckTicket = parkingLot.parkVehicle(truck);

        //Wait 65 seconds
        Thread.sleep(65000);

        //Print ticket details
        System.out.println("Car parked at Spot: " + carTicket.getParkingSpot().getSpotNumber());
        System.out.println("Car Ticket ID: " + carTicket.getTicketId());
        parkingLot.unparkVehicle(carTicket);
        System.out.println();

        System.out.println("Bike parked at Spot: " + bikeTicket.getParkingSpot().getSpotNumber());
        System.out.println("Bike Ticket ID: " + bikeTicket.getTicketId());
        parkingLot.unparkVehicle(bikeTicket);
        System.out.println();

        System.out.println("Truck parked at Spot: " + truckTicket.getParkingSpot().getSpotNumber());
        System.out.println("Truck Ticket ID: " + truckTicket.getTicketId());
        parkingLot.unparkVehicle(truckTicket);
        System.out.println();

    
    }
}
