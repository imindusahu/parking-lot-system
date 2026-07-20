package com.indu.parkinglot.command;

import com.indu.parkinglot.model.Ticket;
import com.indu.parkinglot.model.Vehicle;
import com.indu.parkinglot.service.ParkingLot;

public class ParkVehicleCommand implements Command {

    private ParkingLot parkingLot;
    private Vehicle vehicle;
    private Ticket ticket;

    public ParkVehicleCommand(ParkingLot parkingLot, Vehicle vehicle) {
        this.parkingLot = parkingLot;
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        ticket = parkingLot.parkVehicle(vehicle);
    }

    public Ticket getTicket() {
        return ticket;
    }
}