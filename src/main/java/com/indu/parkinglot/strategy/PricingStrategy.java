package com.indu.parkinglot.strategy;

public interface PricingStrategy {
    
    double calculateFee(long hours);
}