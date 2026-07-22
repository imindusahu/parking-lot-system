package com.indu.parkinglot.payment;

public class UpiPayment implements PaymentStrategy {

    @Override
    public PaymentStatus pay(double amount) {
        System.out.println("Paid Rs." + amount + " using UPI");

        return PaymentStatus.SUCCESS;
    }
}