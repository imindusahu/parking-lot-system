package com.indu.parkinglot;

import com.indu.parkinglot.model.Vehicle;
import com.indu.parkinglot.factory.VehicleFactory;
import com.indu.parkinglot.service.ParkingLot;
import com.indu.parkinglot.model.Ticket;
import com.indu.parkinglot.command.ParkVehicleCommand;
import com.indu.parkinglot.command.UnparkVehicleCommand;
import com.indu.parkinglot.strategy.spot.NearestSpotStrategy;
import com.indu.parkinglot.payment.UpiPayment;
import com.indu.parkinglot.payment.CashPayment;
import com.indu.parkinglot.payment.CardPayment;

public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        VehicleFactory factory = new VehicleFactory();

        Vehicle car = factory.createVehicle("CAR", "UP32AB1234");
        Vehicle bike = factory.createVehicle("BIKE", "UP32XY5678");
        Vehicle truck = factory.createVehicle("TRUCK", "UP78PQ9999");

        ParkingLot parkingLot = ParkingLot.getInstance(5);
        parkingLot.setPaymentStrategy(new UpiPayment());
        parkingLot.setSpotSelectionStrategy(new NearestSpotStrategy());
       
        System.out.println("====== Parking Lot ======");

        //Park vehicles
        ParkVehicleCommand parkCar = new ParkVehicleCommand(parkingLot, car);
        parkCar.execute();
        Ticket carTicket = parkCar.getTicket();
        System.out.println("Car parked at Spot: " + carTicket.getParkingSpot().getSpotNumber());
        System.out.println("Car Ticket ID: " + carTicket.getTicketId());
        System.out.println();

        ParkVehicleCommand parkBike = new ParkVehicleCommand(parkingLot, bike);
        parkBike.execute();
        Ticket bikeTicket = parkBike.getTicket();
        System.out.println("Bike parked at Spot: " + bikeTicket.getParkingSpot().getSpotNumber());
        System.out.println("Bike Ticket ID: " + bikeTicket.getTicketId());
        System.out.println();

        ParkVehicleCommand parkTruck = new ParkVehicleCommand(parkingLot, truck);
        parkTruck.execute();
        Ticket truckTicket = parkTruck.getTicket();
        System.out.println("Truck parked at Spot: " + truckTicket.getParkingSpot().getSpotNumber());
        System.out.println("Truck Ticket ID: " + truckTicket.getTicketId());
        System.out.println();


        //Wait 65 seconds
        Thread.sleep(65000);


        //Unpark vehicle
        UnparkVehicleCommand unparkCar = new UnparkVehicleCommand(parkingLot, carTicket);
        unparkCar.execute();
        System.out.println();

        UnparkVehicleCommand unparkBike = new UnparkVehicleCommand(parkingLot, bikeTicket);
        unparkBike.execute();
        System.out.println();
        
        UnparkVehicleCommand unparkTruck = new UnparkVehicleCommand(parkingLot, truckTicket);
        unparkTruck.execute();
        System.out.println();

    
    }
}
