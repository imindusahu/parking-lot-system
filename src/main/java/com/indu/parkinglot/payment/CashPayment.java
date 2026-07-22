package com.indu.parkinglot.payment;

public class CashPayment implements PaymentStrategy {

    @Override
    public PaymentStatus pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Cash");

        return PaymentStatus.SUCCESS;
    }
}