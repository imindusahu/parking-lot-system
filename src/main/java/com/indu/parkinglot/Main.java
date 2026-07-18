package com.indu.parkinglot;

import com.indu.parkinglot.model.Vehicle;
import com.indu.parkinglot.factory.VehicleFactory;
import com.indu.parkinglot.service.ParkingLot;
import com.indu.parkinglot.model.Ticket;

public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        VehicleFactory factory = new VehicleFactory();

        Vehicle car = factory.createVehicle("CAR", "UP32AB1234");
        Vehicle bike = factory.createVehicle("BIKE", "UP32XY5678");
        Vehicle truck = factory.createVehicle("TRUCK", "UP78PQ9999");

        ParkingLot parkingLot = ParkingLot.getInstance(5);
       
        System.out.println("====== Parking Lot ======");

        //Park vehicles
        Ticket carTicket = parkingLot.parkVehicle(car);
        System.out.println("Car parked at Spot: " + carTicket.getParkingSpot().getSpotNumber());
        System.out.println("Car Ticket ID: " + carTicket.getTicketId());
        System.out.println();

        Ticket bikeTicket = parkingLot.parkVehicle(bike);
        System.out.println("Bike parked at Spot: " + bikeTicket.getParkingSpot().getSpotNumber());
        System.out.println("Bike Ticket ID: " + bikeTicket.getTicketId());
        System.out.println();

        Ticket truckTicket = parkingLot.parkVehicle(truck);
        System.out.println("Truck parked at Spot: " + truckTicket.getParkingSpot().getSpotNumber());
        System.out.println("Truck Ticket ID: " + truckTicket.getTicketId());
        System.out.println();


        //Wait 65 seconds
        Thread.sleep(65000);


        //Unpark vehicle
        parkingLot.unparkVehicle(carTicket);
        System.out.println();

        parkingLot.unparkVehicle(bikeTicket);
        System.out.println();
        
        parkingLot.unparkVehicle(truckTicket);
        System.out.println();

    
    }
}
