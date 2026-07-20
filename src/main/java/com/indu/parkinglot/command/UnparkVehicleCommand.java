package com.indu.parkinglot.command;

import com.indu.parkinglot.model.Ticket;
import com.indu.parkinglot.service.ParkingLot;

public class UnparkVehicleCommand implements Command {

    private ParkingLot parkingLot;
    private Ticket ticket;

    public UnparkVehicleCommand(ParkingLot parkingLot, Ticket ticket) {
        this.parkingLot = parkingLot;
        this.ticket = ticket;
    }

    @Override
    public void execute() {
        parkingLot.unparkVehicle(ticket);
    }
}