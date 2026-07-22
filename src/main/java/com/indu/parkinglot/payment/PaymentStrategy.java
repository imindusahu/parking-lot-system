package com.indu.parkinglot.payment;

public interface PaymentStrategy {
    
    PaymentStatus pay(double amount);
}