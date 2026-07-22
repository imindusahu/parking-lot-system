package com.indu.parkinglot.payment;

public class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Payment of Rs." + amount + " received via UPI.");
    }
}