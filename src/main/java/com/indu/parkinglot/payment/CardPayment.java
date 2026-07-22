package com.indu.parkinglot.payment;

public class CardPayment implements PaymentStrategy {

    @Override
    public PaymentStatus pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Card");

        return PaymentStatus.SUCCESS;
    }
}