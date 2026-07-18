package com.indu.parkinglot.strategy;

public class HourlyPricingStrategy implements PricingStrategy {
    
    @Override
    public double calculateFee(long hours) {
        return hours * 20;
    }
}