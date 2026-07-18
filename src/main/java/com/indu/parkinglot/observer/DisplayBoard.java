package com.indu.parkinglot.observer;

public class DisplayBoard implements ParkingLotObserver {

    @Override 
    public void update(int availableSpots) {
        System.out.println();
        System.out.println("======= Display Board =======");
        System.out.println("Available Spots : " + availableSpots);
        System.out.println();
    }
}